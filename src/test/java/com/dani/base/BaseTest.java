package com.dani.base;

import com.dani.config.ConfigReader;
import com.dani.driver.DriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

public abstract class BaseTest {

    protected WebDriver driver;

    @BeforeEach
    void setUp() {
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.baseUrl());
    }

    @AfterEach
    void tearDown() {
        DriverFactory.quitDriver();
    }
}
