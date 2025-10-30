package com.outsera.api.hooks;

import com.outsera.api.commons.ApiRequest;
import com.outsera.api.stepdefinitions.BrandsSteps;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.response.Response;

public class HooksApi {

    private static boolean tokenObtido = false;

    /**
     * Hook que autentica automaticamente o usuário antes de cenários marcados com @api-login
     */
    @Before("@api-login")
    public void autenticarUsuarioParaLogin() {
        if (!tokenObtido) {
            System.out.println("Autenticando usuário via login API...");

            String email = "admin@practicesoftwaretesting.com";
            String senha = "welcome01";

            String payload = String.format("""
                {
                    "email": "%s",
                    "password": "%s"
                }
            """, email, senha);

            ApiRequest apiRequest = new ApiRequest()
                    .definirMetodo("POST")
                    .definirEndpoint("/users/login")
                    .definirPayload(payload);

            Response response = apiRequest.executar().getResponse();

            if (response.getStatusCode() == 200) {
                String token = response.jsonPath().getString("access_token");
                System.out.println("Token obtido com sucesso: " + token);
                ApiRequest.setAuthToken(token);
                tokenObtido = true;
            } else {
                throw new RuntimeException("Falha ao autenticar via login: " +
                        response.getBody().asString());
            }
        } else {
            System.out.println("Token já obtido, reutilizando...");
        }
    }

    /**
     * Hook que cria uma marca temporária antes de cenários que vão alterar ou deletar
     */
    @Before("@prepareBrand")
    public void criarMarcaTemporariaParaTeste() {
        System.out.println("Criando marca temporária para PUT/DELETE...");

        String payload = """
            {
              "name": "marcaTemp",
              "slug": "marcatemp"
            }
            """;

        ApiRequest apiRequest = new ApiRequest();
        Response response = apiRequest
                .definirMetodo("POST")
                .definirEndpoint("/brands")
                .definirPayload(payload)
                .executar()
                .getResponse();

        if (response.getStatusCode() != 201) {
            throw new RuntimeException("Falha ao criar marca temporária. Status: " + response.getStatusCode());
        }

        BrandsSteps.createdBrandsId = response.jsonPath().getString("id");
        System.out.println("✅ Marca temporária criada com ID: " + BrandsSteps.createdBrandsId);
    }

    /**
     * Hook que limpa a marca criada após cenários de teste
     */
    @After("@api")
    public void cleanupAfterScenario() {
        String id = BrandsSteps.createdBrandsId;

        if (id != null && !id.isEmpty()) {
            System.out.println("Limpando ambiente: excluindo marca criada (" + id + ")...");

            ApiRequest deleteRequest = new ApiRequest()
                    .definirMetodo("DELETE")
                    .definirEndpoint("/brands/" + id)
                    .executar();

            int status = deleteRequest.getResponse().getStatusCode();
            if (status == 200 || status == 204) {
                System.out.println("Marca excluída com sucesso! (status " + status + ")");
            } else {
                System.err.println("Falha ao excluir marca: status " + status);
                deleteRequest.getResponse().then().log().all();
            }

            BrandsSteps.createdBrandsId = null;
        } else {
            System.out.println("ℹ️ Nenhuma marca para excluir neste cenário.");
        }
    }
}