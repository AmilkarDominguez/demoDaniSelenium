package com.dani.tests;

import com.dani.pages.SystemUsersPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@Order(3)
class SearchUserTest extends BaseTest {

    @Test
    @DisplayName("Se puede buscar por Username y encontrar el usuario creado en AddUserTest")
    void canFindUserCreatedInAddUserTest() {
        String username = AddUserTest.createdUsername;
        assumeTrue(username != null, "AddUserTest debe ejecutarse antes y crear el usuario");

        SystemUsersPage resultsPage = loginAndGoToUsers().searchByUsername(username);

        assertEquals(1, resultsPage.getResultsCount(),
                "La busqueda por username deberia devolver exactamente un resultado");
        assertTrue(resultsPage.isUsernamePresentInResults(username));
    }
}
