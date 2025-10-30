package com.outsera.api.stepdefinitions;

import com.outsera.api.commons.ApiRequest;
import io.cucumber.java.pt.*;
import io.restassured.response.Response;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LoginSteps {

    private final ApiRequest apiRequest = new ApiRequest();
    private Response response;

    @Dado("que eu faço uma requisição GET para {string}")
    public void requisicaoGet(String endpoint) {
        apiRequest.definirMetodo("GET").definirEndpoint(endpoint);
    }

    @Dado("que eu faço uma requisição POST para {string} com email {string} e senha {string}")
    public void requisicaoPostComCredenciais(String endpoint, String email, String senha) {
        apiRequest.definirMetodo("POST").definirEndpoint(endpoint);
        String payload = String.format("""
            {
                "email": "%s",
                "password": "%s"
            }
        """, email, senha);
        apiRequest.definirPayload(payload);
    }

    @Quando("eu executo a requisição de login")
    public void executarRequisicao() {
        response = apiRequest.executar().getResponse();
        response.then().log().all();
    }

    @Entao("a resposta do login deve ter status {int}")
    public void validarStatuslogin (int statusEsperado) {
        int statusAtual = response.getStatusCode();
        System.out.printf("Validando status esperado: %d | obtido: %d%n", statusEsperado, statusAtual);
        assertEquals(statusEsperado, statusAtual);
    }

    @Entao("a resposta deve conter a mensagem de erro {string}")
    public void validarMensagemErro(String mensagemEsperada) {
        if (mensagemEsperada.equals("null")) {
            System.out.println("ℹ️ Nenhuma mensagem de erro esperada.");
            return;
        }
        String mensagemObtida = response.jsonPath().getString("error");
        System.out.printf("🔎 Validando mensagem de erro: esperado = %s | obtido = %s%n", mensagemEsperada, mensagemObtida);
        assertEquals(mensagemEsperada, mensagemObtida);
    }

    @Entao("o token de autenticacao foi gerado com sucesso")
    public void validarTokenGerado() {
        String token = ApiRequest.getAuthToken();
        System.out.println("Token obtido no step: " + token);
        assertNotNull("Token não foi gerado!", token);
        ApiRequest.setAuthToken(token);
    }
}
