# language: pt
Funcionalidade: MOBILE - Validação do cliclo de autenticação do usuário

  @mobile @ignore
  Cenario: Login com sucesso na interface mobile
    Dado que o usuário acessa o sistema através do dispositivo mobile
    E seleciona o menu superior Sing in
    Quando através do mobile informa o usuário: "customer@practicesoftwaretesting.com" e senha "welcome01"
    E clica no botão login através do dispositivo
    Então o usuário é redirecionado para a página inicial "My account" da interface mobile