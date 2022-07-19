package SwagLabs.inventory;

import SwagLabs.InventoryPage;
import SwagLabs.LoginPage;
import SwagLabs.TextReportLog;
import com.codeborne.selenide.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.exactTexts;

class SortProductsTest implements TextReportLog {

    private final LoginPage loginPage = Selenide.page(LoginPage.class);
    private final InventoryPage inventoryPage = Selenide.page(InventoryPage.class);

    @BeforeEach
    public void setup() {
        Configuration.holdBrowserOpen = true;
        loginPage.open()
                .loginAs("standard_user", "secret_sauce");
    }

    @AfterEach
    public void afterEach() {
        Selenide.clearBrowserLocalStorage();
    }

    @Test
    public void shouldBeAbleToSortFromAToZ() {
        //when
        inventoryPage.sortBy.selectOption("Name (A to Z)");
        //then
        inventoryPage.inventoryItemsNames.shouldHave(exactTexts(
                "Sauce Labs Backpack",
                "Sauce Labs Bike Light",
                "Sauce Labs Bolt T-Shirt",
                "Sauce Labs Fleece Jacket",
                "Sauce Labs Onesie",
                "Test.allTheThings() T-Shirt (Red)"));
    }

    @Test
    public void shouldBeAbleToSortFromZToA() {
        //when
        inventoryPage.sortBy.selectOption("Name (Z to A)");
        //then
        inventoryPage.inventoryItemsNames.first()
                .shouldHave(Condition.exactText("Test.allTheThings() T-Shirt (Red)"));
    }

    @Test
    public void shouldBeAbleToSortByPriceLowToHigh() {
        //when
        inventoryPage.sortBy.selectOption("Price (low to high)");
        //then
        inventoryPage.inventoryItemsNames.shouldHave(exactTexts(
                "Sauce Labs Onesie",
                "Sauce Labs Bike Light",
                "Sauce Labs Bolt T-Shirt",
                "Test.allTheThings() T-Shirt (Red)",
                "Sauce Labs Backpack",
                "Sauce Labs Fleece Jacket"));
    }

    @Test
    public void shouldBeAbleToSortByPriceHighToLow() {
        //when
        inventoryPage.sortBy.selectOption("Price (high to low)");
        //then
        inventoryPage.inventoryItemsNames.shouldHave(exactTexts(
                "Sauce Labs Fleece Jacket",
                "Sauce Labs Backpack",
                "Sauce Labs Bolt T-Shirt",
                "Test.allTheThings() T-Shirt (Red)",
                "Sauce Labs Bike Light",
                "Sauce Labs Onesie"));
    }

}