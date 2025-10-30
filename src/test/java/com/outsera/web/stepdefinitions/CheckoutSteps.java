package com.outsera.web.stepdefinitions;

import com.outsera.web.commons.AllureManager;
import com.outsera.web.driver.DriverManager;
import com.outsera.web.pages.CheckoutPage;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.openqa.selenium.WebDriver;
import org.slf4j.LoggerFactory;

import java.util.logging.Logger;

public class CheckoutSteps {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(LoginSteps.class);
    private Logger logger = Logger.getLogger(CheckoutSteps.class.getName());
    private WebDriver driver;
    private CheckoutPage checkoutPage;

    public CheckoutSteps(){
        if(driver == null){
            this.driver = DriverManager.getDriver();
        }
        this.checkoutPage = new CheckoutPage(driver);
    }

    @Então("o usuário clica na opção Home da página inicial")
    public void oUsuarioClicaNaOpcaoHomeDaPaginaInicial() {
        checkoutPage.acessarTelaProdutos(driver);
        AllureManager.takeScreenshot(driver, "Página Inicial");
        AllureManager.addTextLog("Página inicial carregada com sucesso");
    }

    @Então("o sistema exibe a tela de produtos com as ferramentas e filtros incluindo {string}")
    public void oSistemaExibeATelaDeProdutosComAsFerramentasEFiltrosIncluindo(String filtro) {
        checkoutPage.validarTelaPesquisaProdutos(driver, filtro);
        AllureManager.takeScreenshot(driver, "Página de Produtos");
        AllureManager.addTextLog("Página de Produtos carregada com sucesso");
    }

    @Então("o usuário seleciona o produto clicando sobre o item {string}")
    public void oUsuarioSelecionaOProdutoClicandoSobreOItem(String produto) {
        checkoutPage.selecionarProdutoLista(driver, produto);
        AllureManager.takeScreenshot(driver, "Página de Produtos");
        AllureManager.addTextLog("Página de Produtos carregada com sucesso");
    }

    @Então("o sistema exibe a tela de detalhes do produto {string}")
    public void oSistemaExibeATelaDeDetalhesDoProduto(String produto) {
        checkoutPage.acessarValidarTelaDoProduto(driver, produto);
        AllureManager.takeScreenshot(driver, "Página de Detalhes do Produto");
        AllureManager.addTextLog("Página de Detalhes do Produto carregada com sucesso");
    }

    @Então("o usuário clica no botão {string}")
    public void oUsuarioClicaNoBotao(String botao) {
        checkoutPage.clicarElementoBotao(driver, botao);
        AllureManager.addTextLog("Evento do botão realizado com sucesso");
    }

    @Então("o sistema adiciona o produto no carrinho com total de {string} unidade")
    public void oSistemaAdicionaOProdutoNoCarrinhoComTotalDeUnidade(String quantidade) {
        checkoutPage.validarTotalItensCarrinhoCompras(driver, quantidade);
        AllureManager.takeScreenshot(driver, "Página de Seleção de Produtos");
        AllureManager.addTextLog("Produto adicionado com sucesso ao carrinho");
    }

    @Então("o usuário clica no carrinho de compras")
    public void oUsuarioClicaNoCarrinhoDeCompras() {
        checkoutPage.clicarCarrinhoCompras(driver);
        AllureManager.addTextLog("Carrinho de compras exibe com sucesso a lista de itens");
    }

    @Então("o sistema exibe a tela do processo de checkout {string}")
    public void oSistemaExibeATelaDoProcessoDeCheckout(String nomeTelacheckout) {
        checkoutPage.validarTelaCheckout(driver, nomeTelacheckout);
        AllureManager.takeScreenshot(driver, "Página de Checkout - Etapa Card");
        AllureManager.addTextLog("Página de Checkout carregada com sucesso");
    }

    @Então("o usuário valida o produto selecionado {string} quantidade {string} e o valor total {string}")
    public void oUsuarioValidaOProdutoSelecionadoQuantidadeEOValorTotal(String produto, String quantidade, String valorTotal) {
        checkoutPage.validarDadosCarrinho(driver, produto, quantidade, valorTotal);
        AllureManager.takeScreenshot(driver, "Página de Checkout");
        AllureManager.addTextLog("Validação dos dados do produto");
    }

    @Quando("o sistema exibe dados os usuário logado {string}")
    public void oSistemaExibeDadosDoUsuarioLogado(String usuarioDados) {
        checkoutPage.validarDadosUsuarioLogadoCheckout(driver, usuarioDados);
        AllureManager.takeScreenshot(driver, "Página de Checkout - Etapa Sign In");
        AllureManager.addTextLog("Página de Checkout usuário validado");
    }

    @Então("o sistema exibe a tela {string}")
    public void oSistemaExibeATela(String nomeTela) {
        checkoutPage.validarNomeTela(driver, nomeTela);
        AllureManager.takeScreenshot(driver, "Página de Checkout");
        AllureManager.addTextLog("Validação da nome da página");
    }

    @Então("o usuário preenche os dados rua {string} cidade {string} estado {string} país {string} e CEP {string}")
    public void oUsuarioPreencheOsDadosRuaCidadeEstadoPaisECEP(String rua, String cidade, String estado, String pais, String cep) {
        checkoutPage.formularioEnderecoEntrega(driver, rua, cidade, estado, pais, cep);
        AllureManager.takeScreenshot(driver, "Página de Checkout - Billing Address");
        AllureManager.addTextLog("Página de Endreço de Cobrança carregada com sucesso");
    }

    @Então("o usuário seleciona o método de pagamento {string}")
    public void oUsuarioSelecionaOMetodoDePagamento(String metodoPagamento) {
        checkoutPage.selecionarFormaPagamento(driver, metodoPagamento);
        AllureManager.takeScreenshot(driver, "Página de Checkout - Payment");
        AllureManager.addTextLog("Página de Payment carregada com sucesso");
    }

    @Então("preenche os dados do formulário Número do Cartão {string} Data de Expiração {string} CVV {string} Titular do Cartão {string}")
    public void preencheOsDadosDoFormularioNumeroDoCartaoDataDeExpiracaoCVVTitularDoCartao(String numeroCartao, String dataExpiracao, String cvv, String titularCartao) {
        checkoutPage.formularioPagamentoCartao(driver, numeroCartao, dataExpiracao, cvv, titularCartao);
        AllureManager.takeScreenshot(driver, "Página de Checkout - Paymanet");
        AllureManager.addTextLog("Página de Pagamento validada");
    }

    @Então("o sistema valida os dados de pagamento e exibe a seguinte mensagem {string}")
    public void oSistemaValidaOsDadosDePagamentoEExibeASeguinteMensagem(String mensagem) {
        checkoutPage.validarMensagemSucessoInsucessoPagamento(driver, mensagem);
        AllureManager.takeScreenshot(driver, "Página Avulsa");
        AllureManager.addTextLog("Validação de campos com domínio negocial");
    }
    @Então("o sistema valida os dados e exibe a seguinte mensagem {string}")
    public void oSistemaValidaOsDadosEexibeAseguinteMensagem (String msgErro) {
        checkoutPage.validarMensagemErroCampos (driver, msgErro);
        AllureManager.takeScreenshot(driver, "Página Avulsa");
        AllureManager.addTextLog("Validação de campos com domínio negocial");
    }

    @Então("o sistema valida os dados do formulário e mantém o botão {string} desabilitado")
    public void oSistemaValidaOsDadosDoFormularioMantemBotaoDesabilitado (String nomeBotao) {
        checkoutPage.validarBotaoDesabiliado(driver, nomeBotao);
        AllureManager.takeScreenshot(driver, "Página Avulsa");
        AllureManager.addTextLog("Validação de campos com domínino negocial");
    }
}
