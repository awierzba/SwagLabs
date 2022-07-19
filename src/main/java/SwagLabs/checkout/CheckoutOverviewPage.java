package SwagLabs.checkout;

import com.codeborne.selenide.*;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CheckoutOverviewPage {
    private final SelenideElement finnishButton = $("[data-test=finish]");
    public final ElementsCollection overviewProductsList = $$(".inventory_item_name");

    public void finnishButtonShouldBeVisible() {
        finnishButton.shouldBe(Condition.visible);
    }

    public void openCheckoutOverview() {
        Selenide.open(Configuration.baseUrl + "/checkout-step-two.html");
    }
}
