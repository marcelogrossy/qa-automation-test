package com.outsera.web.commons;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;

public class AllureManager {

    /**
     * Método para gerar a evidência do printscreen da tela
     *
     * @param driver
     * @param screenshotName
     */
    public static void takeScreenshot(WebDriver driver, String screenshotName) {
        try {
            if (driver instanceof TakesScreenshot) {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                Allure.addAttachment(screenshotName,
                        new ByteArrayInputStream(screenshot));
            }
        } catch (Exception e) {
            System.err.println("Erro ao capturar screenshot para Allure: " + e.getMessage());
        }
    }

    /**
     * Método para gerar a evidência de logs
     * @param message
     */
    public static void addTextLog(String message) {
        Allure.addAttachment("Log", "text/plain", message);
    }

    /**
     * Método para anexar o json aos testes
     *
     * @param jsonContent
     * @param attachmentName
     */
    public static void addJsonAttachment(String jsonContent, String attachmentName) {
        Allure.addAttachment(attachmentName, "application/json", jsonContent);
    }
}
