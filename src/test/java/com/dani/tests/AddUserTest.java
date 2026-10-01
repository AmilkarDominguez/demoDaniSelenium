package com.dani.tests;

import com.dani.pages.SystemUsersPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Order(2)
class AddUserTest extends BaseTest {

    static String createdUsername;

    @Test
    @DisplayName("Se puede registrar un nuevo usuario y queda visible en el listado")
    void newUserIsSavedAndAppearsInTheList() {
        String username = "dani-q_" + System.currentTimeMillis();
        loginAndGoToUsers().clickAdd()
                .selectUserRole("ESS")
                .selectEmployee("a")
                .selectStatus("Enabled")
                .typeUsername(username)
                .typePassword(NEW_USER_PASSWORD)
                .save();

        SystemUsersPage usersPage = new SystemUsersPage(driver);
        assertTrue(usersPage.isUsernamePresentInResults(username),
                "El usuario '" + username + "' deberia aparecer en el listado tras guardarlo");
        createdUsername = username;
    }
}
