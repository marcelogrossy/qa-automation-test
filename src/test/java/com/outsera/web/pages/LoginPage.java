package com.outsera.web.pages;

import com.outsera.web.commons.ConfigReader;
import com.outsera.web.commons.HighlightElement;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.logging.Logger;

public class LoginPage {

    @FindBy(xpath = "//input[@id='email']")
    private WebElement txtLoginUsuario;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement txtLoginSenha;

    @FindBy(xpath = "//input[@Value='Login']")
    private WebElement btnLogin;

    @FindBy(xpath = "//a[@id='menu']")
    private WebElement lblUsuarioAutenticado;

    private final WebDriver driver;
    private final WebDriverWait wait;
    private Logger logger = Logger.getLogger(LoginPage.class.getName());

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    public void abrirTelaLogin(WebDriver driver) {
        logger.info("url da aplicação: " + ConfigReader.get("url.web"));
        driver.navigate().to(ConfigReader.get("url.web"));
    }

    public void preencherLogin(WebDriver driver, String nomeUsuario, String senhaUsuario) {
        HighlightElement.highlightElement(driver,  txtLoginUsuario, false);
        txtLoginUsuario.sendKeys(nomeUsuario);
        HighlightElement.highlightElement(driver, txtLoginSenha, false);
        txtLoginSenha.sendKeys(senhaUsuario);
    }

    public void clicarBotaoLogin(WebDriver driver) {
        HighlightElement.highlightElement(driver, btnLogin, false);
        btnLogin.click();
    }

    public void validarHomeUsuarioAutenticado(WebDriver driver, String nomeUsuarioLogado) {
        wait.until(ExpectedConditions.visibilityOf(lblUsuarioAutenticado));
        HighlightElement.highlightElement(driver, lblUsuarioAutenticado, false);
        Assert.assertEquals(nomeUsuarioLogado, lblUsuarioAutenticado.getText());
    }
}
