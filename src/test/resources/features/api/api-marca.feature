# language: pt
Funcionalidade: API - Validação de Marcas

  @api @marca @api-login
  Cenario: Listar todas as marcas
    Dado que eu configuro uma requisição "GET" para o endpoint "/brands"
    Quando eu executo a requisição
    Então a resposta deve ter status 200
    E a resposta deve conter uma lista no campo "$"

  @api @marca @api-login
  Cenario: Criar uma nova marca
    Dado que eu configuro uma requisição "POST" para o endpoint "/brands"
    E defino o payload:
      """
      {
        "id": "",
        "name": "marcaQA",
        "slug": "marca"
      }
      """
    Quando eu executo a requisição
    Então a resposta deve ter status 201
    E a resposta deve conter o campo "name" com valor "marcaQA"

  @api @marca @prepareBrand @api-login
  Cenario: Editar uma marca existente
    # O hook @prepareBrand cria automaticamente uma marca temporária
    Quando eu envio uma requisição "PUT" para "/brands/{id}" com atualização
    Então a resposta deve ter status 200
    E o campo "name" atualizado deve ter o valor "marcaEditada"

  @api @marca @prepareBrand @api-login
  Cenario: Excluir uma marca existente
    # O hook @prepareBrand cria automaticamente uma marca temporária
    Quando eu envio uma requisição "DELETE" para "/brands/{id}" com atualização
    Então a resposta deve ter status 204
    E a marca "marcaTemp" não deve ser mais listada na pesquisa
