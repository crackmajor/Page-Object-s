package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataGenerator;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private static Map<String, SelenideElement> elementPage = new HashMap<String, SelenideElement>() {{
        put("loginField", $("[name='login'].input__control"));
        put("passwordField", $("[data-test-id='password'].input input.input__control"));
        put("button", $("[data-test-id='action-login'].button"));
    }};

    public VerificationPage login(DataGenerator.Registration user) {
        elementPage.get("loginField").setValue(user.getLogin());
        elementPage.get("passwordField").setValue(user.getPassword());
        elementPage.get("button").click();
        return new VerificationPage();
    }
}
