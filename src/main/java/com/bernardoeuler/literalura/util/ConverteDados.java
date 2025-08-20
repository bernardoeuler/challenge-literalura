package com.bernardoeuler.literalura.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ConverteDados implements IConverteDados {
    ObjectMapper mapper = new ObjectMapper();

    public <T> T obterDados(String json, Class<T> classe) {
        try {
            System.out.println(json);
            return mapper.readValue(json, classe);
        } catch (Exception e) {
            return null;
        }
    }
}