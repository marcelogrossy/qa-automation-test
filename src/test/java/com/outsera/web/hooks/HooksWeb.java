package com.outsera.web.hooks;

import com.outsera.web.commons.AllureManager;
import com.outsera.web.driver.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.util.logging.Logger;

public class HooksWeb {

    private WebDriver driver;
    static Logger logger = Logger.getLogger(HooksWeb.class.toString());

    /**
     * Método para efetuar a carta e configuração do driver para os testes web
     * o mesmo usa como filtro a tag @web que está anotada nas features
     *
     */
    @Before("@web")
    public void setup() {
        driver = DriverManager.getDriver();
    }

    /**
     * Método para os testes focado no cucumber e junit para a geração dos ralatórios
     * a tag @web está sendo usada para limitar a geração da evidência printscreen apenas para
     * o sitema web
     *
     * @param scenario
     */
    @After("@web")
    public void tearDown(Scenario scenario) {
        try {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            String screenshotName = scenario.getName() + " - " + java.time.LocalTime.now().toString();
            scenario.attach(screenshot, "image/png", screenshotName);
        } catch (Exception e) {
            System.err.println("Erro ao capturar screenshot: " + e.getMessage());
        }
        DriverManager.quitDriver();
    }

    /**
     * Método criado para gerar os logs e prints das telas
     * conforme os testes o foco dos anexos é logs para todos os testes
     * e pritns apenas para testes do tipo web
     * o foco deste método é para a geração dos relatórios da Allure
     * @param scenario
     */
    @After()
    public void afterScenario(Scenario scenario) {
        for(String s : scenario.getSourceTagNames()){
            System.out.println("======== " + s.toString());
            if(s.equals("@web")){
                AllureManager.takeScreenshot(driver, "Cenário " + s.replace("@", "") + "finalizado Evidência: " + scenario.getName() +
                        " - Status: " + (scenario.isFailed() ? "FALHOU" : "PASSOU"));
            }
        }
        AllureManager.addTextLog("Cenário finalizado Logs: " + scenario.getName() +
                " - Status: " + (scenario.isFailed() ? "FALHOU" : "PASSOU"));
    }
}
