package SwagLabs.login;

import SwagLabs.InventoryPage;
import SwagLabs.LoginPage;
import SwagLabs.TextReportLog;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LoginPageTest implements TextReportLog {

    LoginPage loginPage = Selenide.page(LoginPage.class);
    InventoryPage inventoryPage = Selenide.page(InventoryPage.class);

    @BeforeEach
    public void beforeEach() {
        loginPage.open();
    }

    @AfterEach
    public void afterEach() {
        Selenide.clearBrowserLocalStorage();
    }

    @Test
    public void shouldLoginWithValidCredentials() {
        //when
        loginPage.loginAs("standard_user", "secret_sauce");

        //then
        inventoryPage.shoppingCartShouldBeVisible();
        inventoryPage.availableProductsListShouldNotEqualZero();
    }

    @Test
    public void shouldNotLoginWithInvalidPassword() {
        //when
        loginPage.loginAs("standard_user", "tomato18");

        //then
        loginPage.errorMessageShouldHave(
                "Epic sadface: Username and password do not match any user in this service");
    }

    @Test
    public void shouldNotLoginWithInvalidCredentials() {
        //when
        loginPage.loginAs("admin", "admin");

        //then
        loginPage.errorMessageShouldHave(
                "Epic sadface: Username and password do not match any user in this service");
    }

    @Test
    public void shouldNotLoginWhenUserIsLockedOut() {
        //when
        loginPage.loginAs("locked_out_user", "secret_sauce");

        //then
        loginPage.errorMessageShouldHave("Epic sadface: Sorry, this user has been locked out.");
    }

}