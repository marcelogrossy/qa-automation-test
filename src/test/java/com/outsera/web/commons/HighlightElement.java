package com.outsera.web.commons;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HighlightElement {

    /**
     * Méotodo para dar foco nos elementos html que o selenium interage
     *
     * @param driver: driver configurado para aplicar a solução
     * @param element: o componente que receberá o foco
     * @param validacao: caso seja false, apenas etapas o highlight volta ao original
     *                 caso seja true o highlight permanece para poder gerar a evidência
     */
    public static void highlightElement(WebDriver driver, WebElement element, boolean validacao) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String originalStyle = element.getAttribute("style");
        js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "background: yellow; border: 2px solid red;");
        if(!validacao){
            new WebDriverWait(driver, Duration.ofSeconds(1)).until(ExpectedConditions.attributeContains(element, "style", "background: yellow; border: 2px solid red;"));
            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, originalStyle);
        }
    }
}
