package com.bernardoeuler.literalura.repository;

import java.util.List;
import java.util.Optional;

import com.bernardoeuler.literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    Optional<Livro> findByTitulo(String titulo);
    List<Livro> findByIdioma(String idioma);
}