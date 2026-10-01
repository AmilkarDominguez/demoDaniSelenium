package com.dani.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends BasePage {

    private final By breadcrumbHeader = By.cssSelector(".oxd-topbar-header-breadcrumb h6");
    private final By adminMenuLink = By.xpath("//a[contains(@class,'oxd-main-menu-item') and normalize-space()='Admin']");

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public String getPageHeaderText() {
        return getText(breadcrumbHeader);
    }

    public SystemUsersPage goToAdminUsers() {
        click(adminMenuLink);
        return new SystemUsersPage(driver);
    }
}
