package com.dani.driver;

import com.dani.config.ConfigReader;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public final class DriverFactory {

    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    private DriverFactory() {
    }

    public static WebDriver getDriver() {
        if (DRIVER.get() == null) {
            DRIVER.set(createDriver());
        }
        return DRIVER.get();
    }

    public static void quitDriver() {
        WebDriver driver = DRIVER.get();
        if (driver != null) {
            driver.quit();
            DRIVER.remove();
        }
    }

    private static WebDriver createDriver() {
        String browser = ConfigReader.browser().toLowerCase();
        boolean headless = ConfigReader.headless();

        WebDriver driver = switch (browser) {
            case "firefox" -> createFirefoxDriver(headless);
            case "edge" -> createEdgeDriver(headless);
            case "chrome" -> createChromeDriver(headless);
            default -> throw new IllegalArgumentException("Navegador no soportado: " + browser);
        };

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigReader.implicitWaitSeconds()));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(ConfigReader.pageLoadTimeoutSeconds()));
        if (headless) {
            driver.manage().window().setSize(new Dimension(1920, 1080));
        } else {
            driver.manage().window().maximize();
        }
        return driver;
    }

    private static WebDriver createChromeDriver(boolean headless) {
        ChromeOptions options = new ChromeOptions();
        if (headless) {
            options.addArguments("--headless=new");
        }
        options.addArguments("--remote-allow-origins=*");
        return new ChromeDriver(options);
    }

    private static WebDriver createFirefoxDriver(boolean headless) {
        FirefoxOptions options = new FirefoxOptions();
        if (headless) {
            options.addArguments("-headless");
        }
        return new FirefoxDriver(options);
    }

    private static WebDriver createEdgeDriver(boolean headless) {
        EdgeOptions options = new EdgeOptions();
        if (headless) {
            options.addArguments("--headless=new");
        }
        return new EdgeDriver(options);
    }
}
