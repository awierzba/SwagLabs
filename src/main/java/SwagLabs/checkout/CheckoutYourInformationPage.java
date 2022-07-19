package SwagLabs.checkout;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CheckoutYourInformationPage {
    private final SelenideElement firstNameField = $("[data-test=firstName");
    private final SelenideElement lastNameField = $("[data-test=lastName]");
    private final SelenideElement postalCodeField = $("[data-test=postalCode]");
    private final SelenideElement continueButton = $("[data-test=continue]");

    public void openCheckoutInformation() {
        Selenide.open(Configuration.baseUrl + "/checkout-step-one.html");
    }

    public void insertCheckoutInformation(String firstName, String lastName, String postalCode) {
        firstNameField.setValue(firstName);
        lastNameField.setValue(lastName);
        postalCodeField.setValue(postalCode);
        continueButton.click();
    }

}
