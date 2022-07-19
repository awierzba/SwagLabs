package SwagLabs;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.CollectionCondition.sizeNotEqual;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class InventoryPage {
    private final SelenideElement shoppingCart = $("#shopping_cart_container");
    public final SelenideElement shoppingCartBadge = $(".shopping_cart_badge");
    private final ElementsCollection availableProductsList = $$(".inventory_list");
    public final SelenideElement sortBy = $(".product_sort_container");
    public final ElementsCollection inventoryItemsNames = $$(".inventory_item_name");

    public void shoppingCartShouldBeVisible() {
        shoppingCart.shouldBe(Condition.visible);
    }

    public void availableProductsListShouldNotEqualZero() {
        availableProductsList.shouldHave(sizeNotEqual(0));
    }

    public void selectProduct(String itemName){
        Selenide.$x(String.format(
                "//*[@class='inventory_item' and .//*[@class='inventory_item_name' and text()='%s']]//button",
                itemName))
                .click();
    }

}
