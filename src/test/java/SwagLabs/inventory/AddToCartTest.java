package SwagLabs.inventory;

import SwagLabs.CartPage;
import SwagLabs.InventoryPage;
import SwagLabs.LoginPage;
import SwagLabs.TextReportLog;
import SwagLabs.checkout.CheckoutOverviewPage;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.exactText;

public class AddToCartTest implements TextReportLog {
    private final List<String> productsToAdd = List.of(
            "Sauce Labs Backpack", "Sauce Labs Bike Light", "Sauce Labs Bolt T-Shirt");
    private final LoginPage loginPage = Selenide.page(LoginPage.class);
    private final InventoryPage inventoryPage = Selenide.page(InventoryPage.class);

    @BeforeEach
    public void setup() {
        Configuration.holdBrowserOpen = true;
        loginPage.open()
                .loginAs("standard_user", "secret_sauce");
        productsToAdd.forEach(inventoryPage::selectProduct);
    }

    @AfterEach
    public void cleanup() {
        Selenide.clearBrowserLocalStorage();
    }

    @Test
    public void shouldShowNumberOfItemsInCart() {
        //then
        inventoryPage.shoppingCartBadge.shouldHave(exactText("3"));
    }

    @Test
    public void selectedItemsShouldBeInTheCartPage() {
        //when
        inventoryPage.shoppingCartBadge.click();

        //then
        Selenide.page(CartPage.class).inventoryItemsNames.shouldHave(exactTexts(productsToAdd));
    }

    @Test
    public void selectedItemsShouldBeInCheckoutOverview() {
        //given
        CheckoutOverviewPage checkoutOverviewPage = Selenide.page(CheckoutOverviewPage.class);
        //when
        checkoutOverviewPage.openCheckoutOverview();
        //then
        checkoutOverviewPage.overviewProductsList.shouldHave(exactTexts(productsToAdd));
    }

}
