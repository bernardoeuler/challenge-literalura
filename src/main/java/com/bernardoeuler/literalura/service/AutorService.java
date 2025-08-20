package com.bernardoeuler.literalura.service;

import java.util.List;

import com.bernardoeuler.literalura.model.Autor;
import com.bernardoeuler.literalura.repository.AutorRepository;
import org.springframework.stereotype.Service;

@Service
public class AutorService {

    private final AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public void salvarAutorSeNaoExistir(Autor autor) {
        autorRepository.findByNome(autor.getNome())
                .orElseGet(() -> {
                    return autorRepository.save(autor);
                });
    }

    public List<Autor> listarTodosAutores() {
        return autorRepository.findAll();
    }

    public List<Autor> listarAutoresVivosNoAno(Integer ano) {
        return autorRepository.findByAnoNascimentoLessThanEqualAndAnoMorteGreaterThanEqualOrAnoMorteIsNull(ano, ano);
    }
}