package com.dani.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    private final By usernameInput = By.name("username");
    private final By passwordInput = By.name("password");
    private final By loginButton = By.cssSelector("button[type='submit']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public DashboardPage loginAs(String username, String password) {
        type(usernameInput, username);
        type(passwordInput, password);
        click(loginButton);
        wait.until(ExpectedConditions.urlContains("dashboard"));
        return new DashboardPage(driver);
    }
}
