package com.outsera.mobile.commons;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class AppiumServerManager {

    private static AppiumDriverLocalService service;

    /**
     * Método para configuração do servidor mobile
     *
     */
    public static void startServer() {
        if (service == null || !service.isRunning()) {
            service = new AppiumServiceBuilder()
                    .usingAnyFreePort()
                    .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                    .withArgument(GeneralServerFlag.LOG_LEVEL, "info")
                    .build();
            service.start();

            // Aguardar até o servidor estar acessível
            int attempts = 0;
            while (!service.isRunning() && attempts < 10) {
                try {
                    Thread.sleep(500); // 0.5s
                    attempts++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (service.isRunning()) {
                System.out.println("Appium Server iniciado: " + service.getUrl());
            } else {
                throw new IllegalStateException("Não foi possível iniciar o Appium Server!");
            }
        }
    }


    /**
     * Método para parar o servidor mobile
     */
    public static void stopServer() {
        if (service != null && service.isRunning()) {
            service.stop();
            System.out.println("Appium Server parado");
        }
    }

    /**
     * Método para validação do servidor mobile
     * @return
     */
    public static String getServerUrl() {
        if (service != null && service.isRunning()) {
            return service.getUrl().toString();
        } else {
            throw new IllegalStateException("Appium Server não está rodando!");
        }
    }
}
