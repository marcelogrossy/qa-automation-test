package com.outsera.api.stepdefinitions;

import com.outsera.api.commons.ApiRequest;
import io.cucumber.java.pt.*;
import io.restassured.response.Response;
import org.junit.Assert;
import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class BrandsSteps {

    private final ApiRequest apiRequest = new ApiRequest();
    private Response response;
    private static final String BASE_ENDPOINT = "/brands";

    public static String createdBrandsId;

    @Dado("que eu configuro uma requisição {string} para o endpoint {string}")
    public void configurarRequisicao(String metodo, String endpoint) {
        apiRequest.definirMetodo(metodo).definirEndpoint(endpoint);
    }

    @E("defino o payload:")
    public void definirPayload(String payloadJson) {
        apiRequest.definirPayload(payloadJson);
    }

    @Quando("eu executo a requisição")
    public void executarRequisicao() {
        response = apiRequest.executar().getResponse();
        response.then().log().all();
    }

    @Entao("a resposta deve ter status {int}")
    public void validarStatus(int statusEsperado) {
        int statusAtual = response.getStatusCode();
        System.out.printf("Validando status esperado: %d | obtido: %d%n", statusEsperado, statusAtual);
        assertEquals(statusEsperado, statusAtual);
        if (statusEsperado == 201) {
            createdBrandsId = response.jsonPath().getString("id");
        }
    }

    @Entao("a resposta deve conter o campo {string} com valor {string}")
    public void validarCampoComValor(String campo, String valorEsperado) {
        String valorObtido = response.jsonPath().getString(campo);
        System.out.printf("Validando campo '%s': esperado = %s | obtido = %s%n", campo, valorEsperado, valorObtido);
        assertEquals(valorEsperado, valorObtido);
    }

    @Entao("a resposta deve conter uma lista no campo {string}")
    public void validarListaNoCampo(String jsonPathCampo) {
        Object campo = response.jsonPath().get(jsonPathCampo);
        assertNotNull("Campo " + jsonPathCampo + " está nulo!", campo);
        assertTrue("O campo não é uma lista!", campo instanceof java.util.List);
        int tamanho = ((java.util.List<?>) campo).size();
        System.out.println("✅ Lista encontrada com " + tamanho + " itens.");
        assertTrue("A lista está vazia!", tamanho > 0);
    }

    @Quando("eu envio uma requisição {string} para {string} com atualização")
    public void enviarRequisicaoAtualizacao(String metodo, String endpoint) {
        String endpointFinal = endpoint.replace("{id}", createdBrandsId);
        if (metodo.equalsIgnoreCase("PUT")) {
            Map<String, Object> body = new HashMap<>();
            body.put("id", createdBrandsId);
            body.put("name", "marcaEditada");
            body.put("slug", "marcaeditada");
            apiRequest.definirMetodo(metodo).definirEndpoint(endpointFinal).setBody(body).executar();
        } else if (metodo.equalsIgnoreCase("DELETE")) {
            apiRequest.definirMetodo(metodo).definirEndpoint(endpointFinal).executar();
        }
        response = apiRequest.getResponse();
        response.then().log().all();
    }

    @Entao("o campo {string} atualizado deve ter o valor {string}")
    public void validarCampoAtualizado(String campo, String valorEsperado) {
        String valorObtido = response.jsonPath().getString(campo);
        if (valorObtido == null) {
            apiRequest.definirMetodo("GET")
                    .definirEndpoint(BASE_ENDPOINT + "/" + createdBrandsId)
                    .executar();
            valorObtido = apiRequest.getResponse().jsonPath().getString(campo);
        }
        assertEquals(valorEsperado, valorObtido);
    }

    @Entao("a marca {string} não deve ser mais listada na pesquisa")
    public void validarMarcaNaoListada(String nomeMarca) {
        Response getResponse = apiRequest.definirMetodo("GET")
                .definirEndpoint(BASE_ENDPOINT)
                .executar()
                .getResponse();

        boolean marcaExiste = getResponse.jsonPath().getList("name").contains(nomeMarca);
        Assert.assertFalse("A marca ainda está presente!", marcaExiste);
        System.out.println("✅ Marca removida com sucesso!");
    }
}
