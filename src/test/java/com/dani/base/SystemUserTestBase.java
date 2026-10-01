package com.dani.base;

import com.dani.pages.SystemUsersPage;

public abstract class SystemUserTestBase extends BaseTest {

    protected static final String ADMIN_USERNAME = "Admin";
    protected static final String ADMIN_PASSWORD = "admin123";
    protected static final String NEW_USER_PASSWORD = "Test@12345";

    protected void createSystemUser(SystemUsersPage usersPage, String username) {
        usersPage.clickAdd()
                .selectUserRole("ESS")
                .selectEmployee("a")
                .selectStatus("Enabled")
                .typeUsername(username)
                .typePassword(NEW_USER_PASSWORD)
                .save();
    }

    protected String uniqueUsername(String prefix) {
        return prefix + "_" + System.currentTimeMillis();
    }
}
