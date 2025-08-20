package com.bernardoeuler.literalura.util;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoApi {
    public String buscarDados(String endereco) {
        System.out.println(endereco);
        URI uri = URI.create(endereco);

        HttpRequest request = HttpRequest.newBuilder().uri(uri).build();
        HttpClient client = HttpClient.newBuilder().followRedirects(HttpClient.Redirect.ALWAYS).build();
        HttpResponse<String> response = null;

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("response: "+response);
        return response.body();
    }
}