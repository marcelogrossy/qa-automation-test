package com.outsera.web.driver;

import com.outsera.web.commons.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Objects;
import java.util.logging.Logger;

public class DriverManager {

    private static WebDriver driver;
    static Logger logger = Logger.getLogger(DriverManager.class.toString());

    private DriverManager() {}

    /**
     * Método para instanciar o driver configurado
     *
     * @return
     */
    public static WebDriver getDriver() {
        if (Objects.isNull(driver)) {
            driver = createDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }
        return driver;
    }

    /**
     * Método para finalizar os testes web
     *
     */
    public static void quitDriver() {
        if (Objects.nonNull(driver)) {
            driver.quit();
            driver = null;
        }
    }

    /**
     * Método para receber as variáveis de execução e tratar o processo de execução
     * browser: chrome default, firefox e edge
     * runMode: local e remoto
     *
     * @return
     */
    private static WebDriver createDriver() {
        String browser = System.getProperty("browser", "chrome").toLowerCase();
        String runMode = System.getProperty("runMode", "local").toLowerCase();
        String runHost = System.getProperty("host.remote", ConfigReader.get("runHost"));

        try {
            switch (runMode) {
                case "remote":
                    return createRemoteDriver(browser, runHost);
                case "local":
                default:
                    return createLocalDriver(browser);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Erro ao criar o WebDriver: " + e.getMessage(), e);
        }
    }

    /**
     * Método para validar a seleção do browser que será usado nos cenários de testes
     *
     * @param browser
     * @return
     */
    private static WebDriver createLocalDriver(String browser) {
        switch (browser) {
            case "firefox":
                return new FirefoxDriver(new FirefoxOptions());
            case "edge":
                return new EdgeDriver(new EdgeOptions());
            case "chrome":
            default:
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--window-size=1920,1080");
                options.addArguments("--disable-gpu");
                options.addArguments("--disable-extensions");
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver(new ChromeOptions());
        }
    }

    /**
     * Metodo focado para a configuração do driver para execução via remoto através do selenium grid.
     *
     * @param browser
     * @param gridUrl
     * @return
     * @throws MalformedURLException
     */
    private static WebDriver createRemoteDriver(String browser, String gridUrl) throws MalformedURLException {
        switch (browser) {
            case "firefox":
                return new RemoteWebDriver(new URL(gridUrl), new FirefoxOptions());
            case "edge":
                return new RemoteWebDriver(new URL(gridUrl), new EdgeOptions());
            case "chrome":
            default:
                return new RemoteWebDriver(new URL(gridUrl), new ChromeOptions());
        }
    }
}
