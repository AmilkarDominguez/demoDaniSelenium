package com.dani.tests;

import com.dani.pages.DashboardPage;
import com.dani.pages.LoginPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Order(1)
class LoginTest extends BaseTest {

    @Test
    @DisplayName("Con credenciales validas, el login lleva al Dashboard")
    void validCredentialsLandOnDashboard() {
        DashboardPage dashboard = new LoginPage(driver).loginAs(ADMIN_USERNAME, ADMIN_PASSWORD);

        assertEquals("Dashboard", dashboard.getPageHeaderText());
        assertTrue(driver.getCurrentUrl().contains("dashboard"));
    }
}
