package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$;

public class PageElements {
    private static Map<String, SelenideElement> elementPage = new HashMap<String, SelenideElement>() {{
        put("loginField", $("[name='login'].input__control"));
        put("passwordField", $("[data-test-id='password'].input input.input__control"));
        put("button", $("[data-test-id='action-login'].button"));
        put("verificationForm", $("[data-test-id='code'].input input.input__control"));
        put("verificationButton", $("[data-test-id='action-verify'].button"));
    }};

    public static SelenideElement getField(String name) {
        return elementPage.get(name);
    }
}
