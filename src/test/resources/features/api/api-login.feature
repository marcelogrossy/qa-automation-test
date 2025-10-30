# language: pt
Funcionalidade: API - Validação do cliclo de autenticação do usuário

  @api @api-login
  Cenario: Login bem-sucedido com credenciais válidas
    Dado que eu faço uma requisição GET para "/users/me"
    Quando eu executo a requisição de login
    Então a resposta do login deve ter status 200
    E o token de autenticacao foi gerado com sucesso

  @api @api-login
  Delineacao do Cenario: Testes de login parametrizados
    Dado que eu faço uma requisição POST para "/users/login" com email "<email>" e senha "<senha>"
    Quando eu executo a requisição de login
    Então a resposta do login deve ter status <status>
    E a resposta deve conter a mensagem de erro "<mensagemErro>"

    Exemplos:
      | email                                | senha         | status | mensagemErro  |
      | admin@practicesoftwaretesting.com    | welcome01     | 200    | null          |
      | invalido@teste.com                   | senhaerrada   | 401    | Unauthorized |
      | admin@practicesoftwaretesting.com    | senhaerrada   | 401    | Unauthorized |


