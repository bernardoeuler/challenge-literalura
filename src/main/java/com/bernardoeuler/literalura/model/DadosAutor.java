package com.bernardoeuler.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosAutor(@JsonAlias("birth_year") Integer anoNascimento,
                         @JsonAlias("death_year") Integer anoMorte,
                         @JsonAlias("name") String nome) {}