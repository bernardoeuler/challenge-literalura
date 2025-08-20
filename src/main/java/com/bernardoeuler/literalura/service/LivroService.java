package com.bernardoeuler.literalura.service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.bernardoeuler.literalura.model.DadosGutendex;
import com.bernardoeuler.literalura.model.DadosLivro;
import com.bernardoeuler.literalura.model.Livro;
import com.bernardoeuler.literalura.repository.AutorRepository;
import com.bernardoeuler.literalura.repository.LivroRepository;
import com.bernardoeuler.literalura.util.ConsumoApi;
import com.bernardoeuler.literalura.util.ConverteDados;

public class LivroService {
    private LivroRepository livroRepository;
    private AutorRepository autorRepository;

    public LivroService(LivroRepository livroRepository, AutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    public Livro buscar(String titulo) {
        String endereco = String.format("https://gutendex.com/books?search=%s", URLEncoder.encode(titulo, StandardCharsets.UTF_8));
        ConsumoApi consumoApi = new ConsumoApi();
        ConverteDados converteDados = new ConverteDados();
        String json = consumoApi.buscarDados(endereco);
        DadosGutendex dadosGutendex = converteDados.obterDados(json, DadosGutendex.class);

        if  (dadosGutendex == null) {
            return null;
        }

        List<DadosLivro> livros = dadosGutendex.livros();

        if  (livros == null || livros.isEmpty()) {
            return null;
        }

        DadosLivro dadosLivro = livros.get(0);

        Livro livro = new Livro(dadosLivro);
        new AutorService(autorRepository).salvarAutorSeNaoExistir(livro.getAutor());
        salvarLivroSeNaoExistir(livro);

        return livro;
    }

    public void salvarLivroSeNaoExistir(Livro livro) {
        livroRepository.findByTitulo(livro.getTitulo())
                .orElseGet(() -> {
                    return livroRepository.save(livro);
                });
    }

    public List<Livro> listarTodosLivros() {
        return livroRepository.findAll();
    }

    public List<Livro> listarLivrosPorIdioma(String idioma) {
        return livroRepository.findByIdioma(idioma);
    }
}