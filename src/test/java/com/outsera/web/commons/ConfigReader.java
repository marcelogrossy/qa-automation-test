package com.outsera.web.commons;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties = new Properties();

    /**
     * Método stático para configurar e ler o arquivo properties com as configurações
     */
    static {
        try (InputStream input = ConfigReader.class
                .getClassLoader()
                .getResourceAsStream("config/config.properties")) {

            if (input == null) {
                throw new RuntimeException("Arquivo application.properties não encontrado");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar resources.properties", e);
        }
    }

    /**
     * Método que retorna o valor da chave do arquivo properties
     *
     * @param key
     * @return
     */
    public static String get(String key) {
        return properties.getProperty(key);
    }
}
