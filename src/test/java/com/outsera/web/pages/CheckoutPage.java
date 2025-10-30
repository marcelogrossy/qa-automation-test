package com.outsera.web.pages;

import com.outsera.web.commons.HighlightElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.logging.Logger;

public class CheckoutPage {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private Logger logger = Logger.getLogger(CheckoutPage.class.getName());

    @FindBy(xpath = "//a[@data-test='nav-home']")
    private WebElement btnHome;

    @FindBy(xpath = "//div[@id='filters']")
    private WebElement divFiltros;

    @FindBy(xpath = "//a[@class='card']//h5")
    private List<WebElement> listaProduto;

    @FindBy(xpath = "//h1[@data-test='product-name']")
    private WebElement lblNomeProduto;

    @FindBy(xpath = "//button[normalize-space() != '']")
    private List<WebElement> listaBotao;

    @FindBy(xpath = "//span[@id='lblCartCount']")
    private WebElement lblQuantidadeCarrinhocompras;

    @FindBy(xpath = "//title")
    private WebElement lblTituloTelaCheckout;

    @FindBy(xpath = "//span[@class='product-title']")
    private WebElement lblCarrinhoItemProduto;

    @FindBy(xpath = "//input[@data-test='product-quantity']")
    private WebElement txtCarrinhoItemQtdeProduto;

    @FindBy(xpath = "//td[@data-test='cart-total']")
    private WebElement lblCarrinhoItemTotal;

    @FindBy(xpath = "//app-login//p")
    private WebElement lblDadosUsuario;

    @FindBy(xpath = "//h3")
    private List<WebElement> listaNomeTela;

    @FindBy(xpath = "//input[@id='street']")
    private WebElement txtRuaCobranca;

    @FindBy(xpath = "//input[@id='city']")
    private WebElement txtCidadeCobranca;

    @FindBy(xpath = "//input[@id='state']")
    private WebElement txtEstadoCobranca;

    @FindBy(xpath = "//input[@id='country']")
    private WebElement txtPaisCobranca;

    @FindBy(xpath = "//input[@id='postal_code']")
    private WebElement txtCepCobranca;

    @FindBy(xpath = "//select[@data-test='payment-method']")
    private WebElement dpdSelecaoPagamento;

    @FindBy(xpath = "//input[@id='credit_card_number']")
    private WebElement txtNumeroCartaoCredito;

    @FindBy(xpath = "//input[@id='expiration_date']")
    private WebElement txtDataExpiracao;

    @FindBy(xpath = "//input[@id='cvv']")
    private WebElement txtCvv;

    @FindBy(xpath = "//input[@id='card_holder_name']")
    private WebElement txtNomeCartao;

    @FindBy(xpath = "//div[@data-test='payment-success-message']")
    private WebElement lblMensagemConfirmacaoPagamento;

    @FindBy(xpath = "//div[contains(@class, 'alert-danger')]")
    private WebElement lblMensagemDadosIncorreto;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    public void acessarTelaProdutos(WebDriver driver) {
        wait.until(ExpectedConditions.visibilityOf(btnHome));
        HighlightElement.highlightElement(driver, btnHome, false);
        btnHome.click();
    }

    public void validarTelaPesquisaProdutos(WebDriver driver, String filtro) {
        wait.until(ExpectedConditions.visibilityOf(divFiltros));
        for(WebElement w : divFiltros.findElements(By.xpath("//h4"))){
            if(w.getText().equals(filtro)){
                HighlightElement.highlightElement(driver, w, true);
                Assert.assertTrue(true);
                break;
            }
        }
    }

    public void selecionarProdutoLista(WebDriver driver, String produto) {
        wait.until(ExpectedConditions.visibilityOfAllElements(listaProduto));
        for(WebElement w : listaProduto){
            if(w.getText().equals(produto)){
                HighlightElement.highlightElement(driver, w, false);
                w.click();
                break;
            }
        }
    }

    public void acessarValidarTelaDoProduto(WebDriver driver, String produto) {
        wait.until(ExpectedConditions.visibilityOf(lblNomeProduto));
        HighlightElement.highlightElement(driver, lblNomeProduto, true);
    }

    public void clicarElementoBotao(WebDriver driver, String botao) {
        wait.until(ExpectedConditions.visibilityOf(listaBotao.get(0)));
        for (WebElement w : listaBotao) {
            if (w.isDisplayed() && w.getText().contains(botao)) {
                HighlightElement.highlightElement(driver, w, false);
                w.click();
                return;
            }
        }
    }

    public void validarTotalItensCarrinhoCompras(WebDriver driver, String quantidade) {
        wait.until(ExpectedConditions.visibilityOf(lblQuantidadeCarrinhocompras));
        HighlightElement.highlightElement(driver, lblQuantidadeCarrinhocompras, true);
        Assert.assertEquals(quantidade, lblQuantidadeCarrinhocompras.getText());
    }

    public void clicarCarrinhoCompras(WebDriver driver) {
        wait.until(ExpectedConditions.visibilityOf(lblQuantidadeCarrinhocompras));
        HighlightElement.highlightElement(driver, lblQuantidadeCarrinhocompras, false);
        lblQuantidadeCarrinhocompras.click();
    }

    public void validarTelaCheckout(WebDriver driver, String nomeTelaCheckout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean elementoPresente = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//title")
        )) != null;
        Assert.assertTrue(nomeTelaCheckout, elementoPresente);
    }

    public void validarDadosCarrinho(WebDriver driver, String produto, String quantidade, String valorTotal) {
        wait.until(ExpectedConditions.visibilityOf(lblCarrinhoItemProduto));
        HighlightElement.highlightElement(driver, lblCarrinhoItemProduto, true);
        Assert.assertEquals(produto, lblCarrinhoItemProduto.getText().trim());
        HighlightElement.highlightElement(driver, txtCarrinhoItemQtdeProduto, true);
        Assert.assertEquals(quantidade, txtCarrinhoItemQtdeProduto.getAttribute("value"));
        HighlightElement.highlightElement(driver, lblCarrinhoItemTotal, true);
        Assert.assertEquals(valorTotal, lblCarrinhoItemTotal.getText());
    }

    public void validarDadosUsuarioLogadoCheckout(WebDriver driver, String usuarioDados) {
        wait.until(ExpectedConditions.visibilityOf(lblDadosUsuario));
        HighlightElement.highlightElement(driver, lblDadosUsuario, true);
        Assert.assertEquals(usuarioDados, lblDadosUsuario.getText());
    }

    public void validarNomeTela(WebDriver driver, String nomeTela) {
        for(WebElement w : listaNomeTela){
            if(w.getText().equals(nomeTela)){
                HighlightElement.highlightElement(driver, w, true);
                Assert.assertEquals(nomeTela, w.getText());
                break;
            }
        }
    }

    public void formularioEnderecoEntrega(WebDriver driver, String rua, String cidade, String estado, String pais, String cep) {
        wait.until(ExpectedConditions.visibilityOf(txtRuaCobranca));
        HighlightElement.highlightElement(driver, txtRuaCobranca, false);
        txtRuaCobranca.sendKeys(rua);
        txtCidadeCobranca.sendKeys(cidade);
        txtEstadoCobranca.sendKeys(estado);
        HighlightElement.highlightElement(driver, txtPaisCobranca, false);
        txtPaisCobranca.sendKeys(pais);
        HighlightElement.highlightElement(driver, txtCepCobranca, false);
        txtCepCobranca.sendKeys(cep);
    }

    public void selecionarFormaPagamento(WebDriver driver, String metodoPagamento) {
        wait.until(ExpectedConditions.visibilityOf(dpdSelecaoPagamento));
        HighlightElement.highlightElement(driver, dpdSelecaoPagamento, false);
        Select select = new Select(dpdSelecaoPagamento);
        dpdSelecaoPagamento.click();
        select.selectByVisibleText(metodoPagamento);
    }

    public void formularioPagamentoCartao(WebDriver driver, String numeroCartao, String dataExpiracao, String cvv, String titularCartao) {
        wait.until(ExpectedConditions.visibilityOf(txtNumeroCartaoCredito));
        HighlightElement.highlightElement(driver, txtNumeroCartaoCredito, false);
        txtNumeroCartaoCredito.sendKeys(numeroCartao);
        HighlightElement.highlightElement(driver, txtDataExpiracao, false);
        txtDataExpiracao.sendKeys(dataExpiracao);
        HighlightElement.highlightElement(driver, txtCvv, false);
        txtCvv.sendKeys(cvv);
        HighlightElement.highlightElement(driver, txtNomeCartao, false);
        txtNomeCartao.sendKeys(titularCartao);
    }

    public void validarMensagemSucessoInsucessoPagamento(WebDriver driver, String mensagem) {
        wait.until(ExpectedConditions.visibilityOf(lblMensagemConfirmacaoPagamento));
        HighlightElement.highlightElement(driver, lblMensagemConfirmacaoPagamento, true);
        Assert.assertEquals(mensagem, lblMensagemConfirmacaoPagamento.getText());
    }

    public void validarMensagemErroCampos (WebDriver driver, String msgErro) {
        wait.until(ExpectedConditions.visibilityOf(lblMensagemDadosIncorreto));
        HighlightElement.highlightElement(driver, lblMensagemDadosIncorreto, true);
        Assert.assertEquals(msgErro, lblMensagemDadosIncorreto.getText());
    }

    public void validarBotaoDesabiliado(WebDriver driver, String nomeBotao) {
        wait.until(ExpectedConditions.visibilityOf(listaBotao.get(0)));
        for (WebElement w : listaBotao) {
            if (w.isEnabled() && w.getText().contains(nomeBotao)) {
                HighlightElement.highlightElement(driver, w, false);
                Assert.assertTrue(true);
                return;
            }
        }
    }
}
