package com.dani.tests;

import com.dani.pages.LoginPage;
import com.dani.pages.SystemUsersPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public abstract class BaseTest {

    protected static final String BASE_URL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
    protected static final String ADMIN_USERNAME = "Admin";
    protected static final String ADMIN_PASSWORD = "admin123";
    protected static final String NEW_USER_PASSWORD = "Test@12345";

    protected WebDriver driver;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(BASE_URL);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    protected SystemUsersPage loginAndGoToUsers() {
        return new LoginPage(driver)
                .loginAs(ADMIN_USERNAME, ADMIN_PASSWORD)
                .goToAdminUsers();
    }
}
