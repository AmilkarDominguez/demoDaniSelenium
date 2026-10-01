package com.dani.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SystemUsersPage extends BasePage {

    private final By addButton = By.xpath("//button[normalize-space()='Add']");
    private final By usernameSearchInput = By.xpath(
            "//label[normalize-space()='Username']/ancestor::div[contains(@class,'oxd-input-group')]//input");
    private final By searchButton = By.xpath("//button[@type='submit' and normalize-space()='Search']");
    private final By tableRows = By.cssSelector(".oxd-table-card");
    private final By usernameCell = By.cssSelector(".oxd-table-cell:nth-child(2)");

    public SystemUsersPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.urlContains("viewSystemUsers"));
        wait.until(ExpectedConditions.presenceOfElementLocated(tableRows));
        waitForTableToLoad();
    }

    public AddUserPage clickAdd() {
        click(addButton);
        return new AddUserPage(driver);
    }

    public SystemUsersPage searchByUsername(String username) {
        type(usernameSearchInput, username);
        click(searchButton);
        waitForTableToLoad();
        wait.ignoring(StaleElementReferenceException.class)
                .until(d -> {
                    var rows = d.findElements(tableRows);
                    return !rows.isEmpty() && rows.stream()
                            .allMatch(row -> row.findElement(usernameCell).getText().equals(username));
                });
        return this;
    }

    public int getResultsCount() {
        return driver.findElements(tableRows).size();
    }

    public boolean isUsernamePresentInResults(String username) {
        return driver.findElements(tableRows).stream()
                .map(row -> row.findElement(usernameCell).getText())
                .anyMatch(username::equals);
    }
}
