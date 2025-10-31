# language: pt
Funcionalidade: WEB - Validação do cliclo de autenticação do usuário

  @web @login-web
  Cenario: Login com sucesso
    Dado que o usuário está na tela de login
    Quando informa o usuário: "customer@practicesoftwaretesting.com" e senha "welcome01"
    E clica no botão login
    Então o usuário é redirecionado para a página inicial e seu nome "Jane Doe" é exibido no cabeçalho da página principal

  @web
  Cenario: Login com insucesso senha incorreta
    Dado que o usuário está na tela de login
    Quando informa o usuário: "customer@practicesoftwaretesting.com" e senha "welcome05"
    E clica no botão login
    Então o sistema valida os dados e exibe a seguinte mensagem "Invalid email or password"

  @web
  Cenario: Login com insucesso usuário inexistente
    Dado que o usuário está na tela de login
    Quando informa o usuário: "usuarioinexistentenull@teste.com" e senha "welcome01"
    E clica no botão login
    Então o sistema valida os dados e exibe a seguinte mensagem "Invalid email or password"

   @web
  Cenario: Login com insucesso usuário inválido
    Dado que o usuário está na tela de login
    Quando informa o usuário: "usuarioinexistentenull" e senha "welcome01"
    E clica no botão login
    Então o sistema valida os dados e exibe a seguinte mensagem "Email format is invalid"

  @web
  Cenario: Login com insucesso usuário vazio
    Dado que o usuário está na tela de login
    Quando informa o usuário: "" e senha "welcome01"
    E clica no botão login
    Então o sistema valida os dados e exibe a seguinte mensagem "Email is required"

  @web
  Cenario: Login com insucesso senha vazio
    Dado que o usuário está na tela de login
    Quando informa o usuário: "customer@practicesoftwaretesting.com" e senha ""
    E clica no botão login
    Então o sistema valida os dados e exibe a seguinte mensagem "Password is required"