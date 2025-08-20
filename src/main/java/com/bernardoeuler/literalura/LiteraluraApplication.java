package com.bernardoeuler.literalura;

import java.util.List;
import java.util.Scanner;

import com.bernardoeuler.literalura.model.Autor;
import com.bernardoeuler.literalura.model.Livro;
import com.bernardoeuler.literalura.repository.AutorRepository;
import com.bernardoeuler.literalura.repository.LivroRepository;
import com.bernardoeuler.literalura.service.AutorService;
import com.bernardoeuler.literalura.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {
    private final Scanner sc = new Scanner(System.in);
    @Autowired
    private LivroRepository livroRepository;
    @Autowired
    private AutorRepository autorRepository;

    public static void main(String[] args) {
        SpringApplication.run(LiteraluraApplication.class, args);
    }

    @Override
    public void run(String... args) {
        while (true) {
            mostrarMenu();
            int opcao = lerOpcao(sc);

            switch (opcao) {
                case 1 -> buscarLivro();
                case 2 -> listarLivros();
                case 3 -> listarAutores();
                case 4 -> listarAutoresVivos();
                case 5 -> listarLivrosPorIdioma();
                case 0 -> { System.out.println("Saindo..."); sc.close(); return; }
                default -> System.out.println("Opção inválida, tente novamente.");
            }
            System.out.println();
        }
    }

    private void mostrarMenu() {
        System.out.println("Escolha o número de sua opção:");
        System.out.println("1 - buscar livro pelo título");
        System.out.println("2 - listar livros registrados");
        System.out.println("3 - listar autores registrados");
        System.out.println("4 - listar autores vivos em um determinado ano");
        System.out.println("5 - listar livros em um determinado idioma");
        System.out.println("0 - sair");
        System.out.print("Opção: ");
    }

    private int lerOpcao(Scanner sc) {
        if (sc.hasNextInt()) {
            int opcao = sc.nextInt();
            sc.nextLine();
            return opcao;
        } else {
            sc.next();
            return -1;
        }
    }

    private void buscarLivro() {
        System.out.print("Digite o nome do livro que deseja buscar: ");
        String titulo = sc.nextLine();
        System.out.println("Buscando o livro " + titulo + "...");
        LivroService livroService = new LivroService(livroRepository, autorRepository);
        Livro livro = livroService.buscar(titulo);

        printLivro(livro);
    }

    private void listarLivros() {
        LivroService livroService = new LivroService(livroRepository, autorRepository);
        livroService.listarTodosLivros().forEach(this::printLivro);
    }

    private void listarAutores() {
        System.out.println("Você escolheu: listar autores registrados");
        AutorService autorService = new AutorService(autorRepository);
        autorService.listarTodosAutores().forEach(this::printAutor);
    }

    private void listarAutoresVivos() {
        System.out.print("Digite o ano para verificar autores vivos: ");
        int ano = sc.nextInt();
        sc.nextLine();

        AutorService autorService = new AutorService(autorRepository);
        List<Autor> vivos = autorService.listarAutoresVivosNoAno(ano);
        if (vivos.isEmpty()) {
            System.out.println("Nenhum autor vivo registrado nesse ano.");
        } else {
            vivos.forEach(this::printAutor);
        }
    }

    private void listarLivrosPorIdioma() {
        System.out.print("Digite o idioma dos livros que deseja listar: ");
        String idioma = sc.nextLine();

        LivroService livroService = new LivroService(livroRepository, autorRepository);
        List<Livro> livros = livroService.listarLivrosPorIdioma(idioma);
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado para o idioma: " + idioma);
        } else {
            livros.forEach(this::printLivro);
        }
    }

    private void printLivro(Livro livro) {
        System.out.println("----- LIVRO -----");
        System.out.println("Título: " + livro.getTitulo());
        System.out.println("Autor: " + livro.getAutor().getNome());
        System.out.println("Idioma: " + livro.getIdioma());
        System.out.println("Downloads: " + livro.getDownloads());
        System.out.println("-----------------");
    }

    private void printAutor(Autor autor) {
        System.out.println("----- AUTOR -----");
        System.out.println("Nome: " + autor.getNome());
        System.out.println("Nascimento: " + autor.getAnoNascimento());
        System.out.println("Morte: " + autor.getAnoMorte());
        System.out.println("-----------------");
    }
}