package com.dani.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AddUserPage extends BasePage {

    private final By userRoleDropdown = By.xpath(
            "//label[normalize-space()='User Role']/ancestor::div[contains(@class,'oxd-input-group')]//div[contains(@class,'oxd-select-text')]");
    private final By statusDropdown = By.xpath(
            "//label[normalize-space()='Status']/ancestor::div[contains(@class,'oxd-input-group')]//div[contains(@class,'oxd-select-text')]");
    private final By employeeNameInput = By.xpath(
            "//label[normalize-space()='Employee Name']/ancestor::div[contains(@class,'oxd-input-group')]//input");
    private final By employeeAutocompleteOptions = By.cssSelector(".oxd-autocomplete-dropdown .oxd-autocomplete-option");
    private final By usernameInput = By.xpath(
            "//label[normalize-space()='Username']/ancestor::div[contains(@class,'oxd-input-group')]//input");
    private final By passwordInput = By.xpath(
            "//label[normalize-space()='Password']/ancestor::div[contains(@class,'oxd-input-group')]//input");
    private final By confirmPasswordInput = By.xpath(
            "//label[normalize-space()='Confirm Password']/ancestor::div[contains(@class,'oxd-input-group')]//input");
    private final By saveButton = By.xpath("//button[@type='submit' and normalize-space()='Save']");

    public AddUserPage(WebDriver driver) {
        super(driver);
        waitVisible(userRoleDropdown);
    }

    public AddUserPage selectUserRole(String role) {
        selectDropdownOption(userRoleDropdown, role);
        return this;
    }

    public AddUserPage selectStatus(String status) {
        selectDropdownOption(statusDropdown, status);
        return this;
    }

    public AddUserPage selectEmployee(String employeeHint) {
        WebElement input = waitVisible(employeeNameInput);
        input.click();
        input.sendKeys(employeeHint);
        wait.until(d -> {
            List<WebElement> options = d.findElements(employeeAutocompleteOptions);
            if (options.isEmpty()) {
                return false;
            }
            String text = options.get(0).getText().trim();
            return !text.isEmpty() && !text.equalsIgnoreCase("Searching....");
        });
        driver.findElements(employeeAutocompleteOptions).get(0).click();
        return this;
    }

    public AddUserPage typeUsername(String username) {
        type(usernameInput, username);
        return this;
    }

    public AddUserPage typePassword(String password) {
        type(passwordInput, password);
        type(confirmPasswordInput, password);
        return this;
    }

    public SystemUsersPage save() {
        click(saveButton);
        return new SystemUsersPage(driver);
    }

    private void selectDropdownOption(By dropdownTrigger, String optionText) {
        click(dropdownTrigger);
        By option = By.xpath("//div[contains(@class,'oxd-select-dropdown')]//span[normalize-space()='" + optionText + "']");
        click(option);
    }
}
