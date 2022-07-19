package SwagLabs.checkout;

import SwagLabs.LoginPage;
import SwagLabs.TextReportLog;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CheckoutTest implements TextReportLog {
    CheckoutYourInformationPage checkoutYourInformationPage =
            Selenide.page(CheckoutYourInformationPage.class);
    CheckoutOverviewPage checkoutOverviewPage = Selenide.page(CheckoutOverviewPage.class);
    LoginPage loginPage = Selenide.page(LoginPage.class);

    @BeforeEach
    public void setup() {
        loginPage.open()
                .loginAs("standard_user", "secret_sauce");
        checkoutYourInformationPage.openCheckoutInformation();
    }

    @AfterEach
    public void afterEach() {
        Selenide.clearBrowserLocalStorage();
    }

    @Test
    public void shouldOpenOverviewAfterInsertingCredentials() {
        //when
        checkoutYourInformationPage.insertCheckoutInformation(
                "Jerry", "Webber", "31-822");
        //then
        checkoutOverviewPage.finnishButtonShouldBeVisible();
    }

}
