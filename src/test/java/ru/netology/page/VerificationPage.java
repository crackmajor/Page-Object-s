package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataGenerator;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private static Map<String, SelenideElement> elementPage = new HashMap<String, SelenideElement>() {{
        put("verificationForm", $("[data-test-id='code'].input input.input__control"));
        put("verificationButton", $("[data-test-id='action-verify'].button"));
    }};

    public CardBalancePage verificationPage(DataGenerator.VerificationCode verificationCode) {
        elementPage.get("verificationForm").shouldBe(Condition.visible);
        elementPage.get("verificationForm").setValue(verificationCode.getCode());
        elementPage.get("verificationButton").click();
        return new CardBalancePage();
    }
}
