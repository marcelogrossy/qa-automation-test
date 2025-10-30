package com.outsera.web.stepdefinitions;

import com.outsera.web.commons.AllureManager;
import com.outsera.web.driver.DriverManager;
import com.outsera.web.pages.LoginPage;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.openqa.selenium.WebDriver;
import org.slf4j.LoggerFactory;

import java.util.logging.Logger;

public class LoginSteps {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(LoginSteps.class);
    private Logger logger = Logger.getLogger(LoginSteps.class.getName());
    private WebDriver driver;
    private LoginPage loginPage;

    public LoginSteps(){
        if(driver == null){
            this.driver = DriverManager.getDriver();
        }
        this.loginPage = new LoginPage(driver);
    }

    @Dado("que o usuário está na tela de login")
    public void queOUsuárioEstáNaTelaDeLogin() {
        loginPage.abrirTelaLogin(driver);
        AllureManager.takeScreenshot(driver, "Página de Login");
        AllureManager.addTextLog("Página de login carregada com sucesso");
    }

    @Quando("informa o usuário: {string} e senha {string}")
    public void informaOUsuárioESenha(String nomeUsuario, String senhaUsuario) {
        loginPage.preencherLogin(driver, nomeUsuario, senhaUsuario);
        AllureManager.takeScreenshot(driver, "Página de Login");
        AllureManager.addTextLog("Página de login carregada com sucesso");
    }

    @Quando("clica no botão login")
    public void clicaNoBotãoLogin() {
        loginPage.clicarBotaoLogin(driver);
        AllureManager.takeScreenshot(driver, "Página de Login");
        AllureManager.addTextLog("Página de login carregada com sucesso");
    }

    @Então("o usuário é redirecionado para a página inicial e seu nome {string} é exibido no cabeçalho da página principal")
    public void oUsuárioÉRedirecionadoParaAPáginaInicialESeuNomeÉExibidoNoCabeçalhoDaPáginaPrincipal(String nomeUsuarioLogado) {
        loginPage.validarHomeUsuarioAutenticado(driver, nomeUsuarioLogado);
        AllureManager.takeScreenshot(driver, "Página de Login");
        AllureManager.addTextLog("Página de login carregada com sucesso");
    }

}
