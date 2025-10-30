# language: pt
Funcionalidade: WEB - Validação do processo de checkout.

  @web
  Cenario: Compra efetuada com sucesso via cartão de crédito
    Dado que o usuário está na tela de login
    Quando informa o usuário: "customer@practicesoftwaretesting.com" e senha "welcome01"
    E clica no botão login
    Então o usuário é redirecionado para a página inicial e seu nome "Jane Doe" é exibido no cabeçalho da página principal
    E o usuário clica na opção Home da página inicial
    Então o sistema exibe a tela de produtos com as ferramentas e filtros incluindo "Price Range"
    Então o usuário seleciona o produto clicando sobre o item "Pliers"
    Quando o sistema exibe a tela de detalhes do produto "Pliers"
    Então o usuário clica no botão "Add to cart"
    Quando o sistema adiciona o produto no carrinho com total de "1" unidade
    Então o usuário clica no carrinho de compras
    Quando o sistema exibe a tela do processo de checkout "Checkout - Practice Software Testing - Toolshop - v5.0"
    Então o usuário valida o produto selecionado "Pliers" quantidade "1" e o valor total "$12.01"
    Então o usuário clica no botão "Proceed to checkout"
    Quando o sistema exibe dados os usuário logado "Hello Jane Doe, you are already logged in. You can proceed to checkout."
    Então o usuário clica no botão "Proceed to checkout"
    Quando o sistema exibe a tela "Billing Address"
    Então o usuário preenche os dados rua "Rua Tancredo Neves, 1000" cidade "Curitiba" estado "Paraná" país "Brasil" e CEP "81270-050"
    Então o usuário clica no botão "Proceed to checkout"
    Quando o sistema exibe a tela "Payment"
    Então o usuário seleciona o método de pagamento "Credit Card"
    E preenche os dados do formulário Número do Cartão "0000-0000-0000-0000" Data de Expiração "12/2028" CVV "999" Titular do Cartão "Tancredo Neves"
    Então o usuário clica no botão "Confirm"
    Então o sistema valida os dados de pagamento e exibe a seguinte mensagem "Payment was successful"

  @web
  Cenario: Compra não finalizada número do cartão incorreto
    Dado que o usuário está na tela de login
    Quando informa o usuário: "customer@practicesoftwaretesting.com" e senha "welcome01"
    E clica no botão login
    Então o usuário é redirecionado para a página inicial e seu nome "Jane Doe" é exibido no cabeçalho da página principal
    E o usuário clica na opção Home da página inicial
    Então o sistema exibe a tela de produtos com as ferramentas e filtros incluindo "Price Range"
    Então o usuário seleciona o produto clicando sobre o item "Pliers"
    Quando o sistema exibe a tela de detalhes do produto "Pliers"
    Então o usuário clica no botão "Add to cart"
    Quando o sistema adiciona o produto no carrinho com total de "1" unidade
    Então o usuário clica no carrinho de compras
    Quando o sistema exibe a tela do processo de checkout "Checkout - Practice Software Testing - Toolshop - v5.0"
    Então o usuário valida o produto selecionado "Pliers" quantidade "1" e o valor total "$12.01"
    Então o usuário clica no botão "Proceed to checkout"
    Quando o sistema exibe dados os usuário logado "Hello Jane Doe, you are already logged in. You can proceed to checkout."
    Então o usuário clica no botão "Proceed to checkout"
    Quando o sistema exibe a tela "Billing Address"
    Então o usuário preenche os dados rua "Rua Tancredo Neves, 1000" cidade "Curitiba" estado "Paraná" país "Brasil" e CEP "81270-050"
    Então o usuário clica no botão "Proceed to checkout"
    Quando o sistema exibe a tela "Payment"
    Então o usuário seleciona o método de pagamento "Credit Card"
    E preenche os dados do formulário Número do Cartão "00000000-0000-0000" Data de Expiração "12/2028" CVV "999" Titular do Cartão "Tancredo Neves"
    Então o sistema valida os dados e exibe a seguinte mensagem "Invalid card number format."

  @web
  Cenario: Compra não finalizada código postal incorreto
    Dado que o usuário está na tela de login
    Quando informa o usuário: "customer@practicesoftwaretesting.com" e senha "welcome01"
    E clica no botão login
    Então o usuário é redirecionado para a página inicial e seu nome "Jane Doe" é exibido no cabeçalho da página principal
    E o usuário clica na opção Home da página inicial
    Então o sistema exibe a tela de produtos com as ferramentas e filtros incluindo "Price Range"
    Então o usuário seleciona o produto clicando sobre o item "Pliers"
    Quando o sistema exibe a tela de detalhes do produto "Pliers"
    Então o usuário clica no botão "Add to cart"
    Quando o sistema adiciona o produto no carrinho com total de "1" unidade
    Então o usuário clica no carrinho de compras
    Quando o sistema exibe a tela do processo de checkout "Checkout - Practice Software Testing - Toolshop - v5.0"
    Então o usuário valida o produto selecionado "Pliers" quantidade "1" e o valor total "$12.01"
    Então o usuário clica no botão "Proceed to checkout"
    Quando o sistema exibe dados os usuário logado "Hello Jane Doe, you are already logged in. You can proceed to checkout."
    Então o usuário clica no botão "Proceed to checkout"
    Quando o sistema exibe a tela "Billing Address"
    Então o usuário preenche os dados rua "Rua Tancredo Neves, 1000" cidade "Curitiba" estado "Paraná" país "Brasil" e CEP "81450-23666"
    Então o sistema valida os dados do formulário e mantém o botão "Proceed to checkout" desabilitado





