package SwagLabs;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selenide.$$;

public class CartPage {
    public final ElementsCollection inventoryItemsNames = $$(".inventory_item_name");
}
