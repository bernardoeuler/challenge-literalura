package com.bernardoeuler.literalura.repository;

import java.util.List;
import java.util.Optional;

import com.bernardoeuler.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNome(String nome);
    List<Autor> findByAnoNascimentoLessThanEqualAndAnoMorteGreaterThanEqualOrAnoMorteIsNull(Integer ano, Integer ano2);
}