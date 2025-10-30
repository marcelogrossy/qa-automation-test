package com.outsera.mobile.stepdefinitions;

import com.outsera.mobile.pages.LoginMobilePage;
import io.cucumber.java.pt.*;

public class LoginMobileSteps {

    private LoginMobilePage loginMobilePage;

    @Dado("que o usuário acessa o sistema através do dispositivo mobile")
    public void acessarSistemaMobile() {

    }

    @E("seleciona o menu superior Sing in")
    public void selecionarMenuSingIn() {

    }

    @Quando("através do mobile informa o usuário: {string} e senha {string}")
    public void informarCredenciais(String usuario, String senha) {


    }

    @E("clica no botão login através do dispositivo")
    public void clicarBotaoLogin() {

    }

    @Então("o usuário é redirecionado para a página inicial {string} da interface mobile")
    public void validarPaginaInicial(String tituloEsperado) {

    }
}
