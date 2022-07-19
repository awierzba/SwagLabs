package SwagLabs;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    private final SelenideElement usernameField = $("[data-test=username]");
    private final SelenideElement passwordField = $("[data-test=password]");
    private final SelenideElement loginButton = $("[data-test=login-button]");
    private final SelenideElement errorMessage = $(".error-message-container");

    public LoginPage open() {
        Selenide.open(Configuration.baseUrl);
        return this;
    }

    public void loginAs(String username, String password) {
        usernameField.setValue(username);
        passwordField.setValue(password);
        loginButton.click();
    }

    public void errorMessageShouldHave(String message) {
        errorMessage.shouldHave(Condition.exactText(message));
    }

}
