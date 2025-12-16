package com.outsera.web.driver;

import com.outsera.web.commons.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
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
import java.util.List;
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

            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

            if (!(driver instanceof RemoteWebDriver)) {
                driver.manage().window().maximize();
            }

            try {
                ((JavascriptExecutor) driver).executeScript(
                        "Object.defineProperty(navigator, 'webdriver', {get: () => undefined})"
                );
            } catch (Exception ignored) {}
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
        String runMode = System.getProperty("runMode"); // NÃO definir default aqui
        String gridUrl = System.getProperty("gridUrl", ConfigReader.get("gridUrl"));

        try {
            // null, vazio ou espaços → LOCAL
            if (runMode == null || runMode.trim().isEmpty()) {
                return createLocalDriver(browser);
            }

            switch (runMode.toLowerCase()) {
                case "local":
                    return createLocalDriver(browser);

                case "remote":
                    return createRemoteDriver(browser, gridUrl);

                default:
                    throw new IllegalArgumentException(
                            "runMode inválido: " + runMode + ". Use 'local' ou 'remote'."
                    );
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
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver(options);
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

        URL hubUrl = new URL(gridUrl);

        switch (browser.toLowerCase()) {
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();

                return new RemoteWebDriver(hubUrl, firefoxOptions);

            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--no-sandbox");
                edgeOptions.addArguments("--disable-dev-shm-usage");

                return new RemoteWebDriver(hubUrl, edgeOptions);

            case "chrome":
            default:
                ChromeOptions chromeOptions = new ChromeOptions();

                chromeOptions.addArguments(
                        "--no-sandbox",
                        "--disable-dev-shm-usage",
                        "--window-size=1920,1080",
                        "--start-maximized",
                        "--lang=pt-BR"
                );

                chromeOptions.addArguments(
                        "--disable-blink-features=AutomationControlled"
                );

                boolean headless = Boolean.parseBoolean(
                        System.getProperty("headless", "true")
                );
                if (headless) {
                    chromeOptions.addArguments("--headless=new");
                }

                chromeOptions.addArguments(
                        "--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
                                "AppleWebKit/537.36 (KHTML, like Gecko) " +
                                "Chrome/121.0.0.0 Safari/537.36"
                );

                chromeOptions.setExperimentalOption(
                        "excludeSwitches", List.of("enable-automation")
                );
                chromeOptions.setExperimentalOption(
                        "useAutomationExtension", false
                );

                return new RemoteWebDriver(hubUrl, chromeOptions);
        }
    }

}
