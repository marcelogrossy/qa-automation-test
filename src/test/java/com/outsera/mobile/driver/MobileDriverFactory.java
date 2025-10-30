package com.outsera.mobile.driver;

import com.outsera.mobile.commons.AppiumServerManager;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class MobileDriverFactory {

    private static AndroidDriver androidDriver;
    private static IOSDriver iosDriver;

    private static boolean isAndroid = true;

    /**
     * Método que configura o driver conforme as capabilities necessárias
     *
     * @throws Exception
     */
    public static void initializeDriver() throws Exception {
        if (isAndroid && androidDriver == null) {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("platformName", "Android");
            caps.setCapability("deviceName", "emulator-5554");
            caps.setCapability("platformVersion", "13.0");
            caps.setCapability("browserName", "Chrome");

            androidDriver = new AndroidDriver(new URL(AppiumServerManager.getServerUrl()), caps);
        }
        // iOS pode ser adicionado aqui
    }

    /**
     * Método que intancia o driver
     *
     * @return
     */
    public static AndroidDriver getDriver() {
        return androidDriver;
    }

    /**
     * Método para finalizar o teste e o driver
     *
     */
    public static void quitDriver() {
        if (androidDriver != null) {
            androidDriver.quit();
            androidDriver = null;
        }
        if (iosDriver != null) {
            iosDriver.quit();
            iosDriver = null;
        }
    }
}
