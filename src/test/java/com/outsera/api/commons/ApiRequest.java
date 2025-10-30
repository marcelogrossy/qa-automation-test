package com.outsera.api.commons;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.Map;

public class ApiRequest {

    private String metodo;
    private String endpoint;
    private String payload;
    private Map<String, Object> body;
    private Response response;
    private static String authToken;

    public ApiRequest definirMetodo(String metodo) {
        this.metodo = metodo;
        return this;
    }

    public ApiRequest definirEndpoint(String endpoint) {
        this.endpoint = endpoint;
        return this;
    }

    public ApiRequest definirPayload(String payload) {
        this.payload = payload;
        return this;
    }

    public ApiRequest setBody(Map<String, Object> body) {
        this.body = body;
        return this;
    }

    public static void setAuthToken(String token) {
        authToken = token;
    }

    public static String getAuthToken() {
        return authToken;
    }

    /**
     * Método genérico para execução dos request
     *
     * @return
     */
    public ApiRequest executar() {
        RequestSpecification request = RestAssured.given()
                .baseUri("https://api.practicesoftwaretesting.com") // ajuste sua base URL
                .header("Content-Type", "application/json");

        if (authToken != null && !authToken.isEmpty()) {
            request.header("Authorization", "Bearer " + authToken);
        }

        if (payload != null) {
            request.body(payload);
        } else if (body != null) {
            request.body(body);
        }

        switch (metodo.toUpperCase()) {
            case "POST": response = request.post(endpoint); break;
            case "GET": response = request.get(endpoint); break;
            case "PUT": response = request.put(endpoint); break;
            case "DELETE": response = request.delete(endpoint); break;
            default: throw new IllegalArgumentException("Método HTTP não suportado: " + metodo);
        }

        return this;
    }

    /**
     * Método para recuperar o response dos request
     * @return
     */
    public Response getResponse() {
        return response;
    }
}
