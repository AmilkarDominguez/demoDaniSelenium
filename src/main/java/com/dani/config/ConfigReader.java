package com.dani.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties PROPERTIES = load();

    private ConfigReader() {
    }

    private static Properties load() {
        Properties properties = new Properties();
        try (InputStream input = ConfigReader.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new IllegalStateException("No se encontro config.properties en el classpath");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new IllegalStateException("Error al cargar config.properties", e);
        }
        return properties;
    }

    public static String get(String key) {
        return System.getProperty(key, PROPERTIES.getProperty(key));
    }

    public static String get(String key, String defaultValue) {
        String value = get(key);
        return value != null ? value : defaultValue;
    }

    public static int getInt(String key, int defaultValue) {
        String value = get(key);
        return value != null ? Integer.parseInt(value) : defaultValue;
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        String value = get(key);
        return value != null ? Boolean.parseBoolean(value) : defaultValue;
    }

    public static String browser() {
        return get("browser", "chrome");
    }

    public static boolean headless() {
        return getBoolean("headless", false);
    }

    public static String baseUrl() {
        return get("baseUrl");
    }

    public static int implicitWaitSeconds() {
        return getInt("implicitWaitSeconds", 5);
    }

    public static int explicitWaitSeconds() {
        return getInt("explicitWaitSeconds", 10);
    }

    public static int pageLoadTimeoutSeconds() {
        return getInt("pageLoadTimeoutSeconds", 30);
    }
}
