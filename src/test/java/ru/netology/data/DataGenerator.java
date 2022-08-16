package ru.netology.data;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.Value;
import lombok.val;
import org.openqa.selenium.Keys;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$;

public class DataGenerator {

    public static class PageElements {

        private static final String balanceStart = "баланс: ";
        private static final String balanceFinish = " р.";

        private static Map<String, String> cards = new HashMap<String, String>() {{
            put("'92df3f1c-a033-48e6-8390-206f6b1f56c0'", "5559 0000 0000 0001");
            put("'0f3f5c2a-249e-4c3d-8287-09f7a039391d'", "5559 0000 0000 0002");
        }};

        private static Map<String, SelenideElement> elementPage = new HashMap<String, SelenideElement>() {{
            put("loginField", $("[name='login'].input__control"));
            put("passwordField", $("[data-test-id='password'].input input.input__control"));
            put("button", $("[data-test-id='action-login'].button"));
            put("verificationForm", $("[data-test-id='code'].input input.input__control"));
            put("verificationButton", $("[data-test-id='action-verify'].button"));
        }};

        public static int getCardBalance(String id) {
            val text = $("[data-test-id=" + id + "]").getText();
            return extractBalance(text);
        }

        public static String getCard(String id) {
            return cards.get(id);
        }

        private static int extractBalance(String text) {
            val start = text.indexOf(balanceStart);
            val finish = text.indexOf(balanceFinish);
            val value = text.substring(start + balanceStart.length(), finish);
            return Integer.parseInt(value);
        }

        public static SelenideElement getField(String name) {
            return elementPage.get(name);
        }

        public static void loginUser(DataGenerator.Registration user) {
            getField("loginField").setValue(user.getLogin());
            getField("passwordField").setValue(user.getPassword());
            getField("button").click();
            getField("verificationForm").shouldBe(Condition.visible);
            getField("verificationForm").setValue(user.getVerificationCode());
            getField("verificationButton").click();
        }

        public static void transferToCard(String card1, String card2, int amount) {
            $("[data-test-id=" + card1 + "] button").click();
            $("[data-test-id='amount'].input input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
            $("[data-test-id='amount'].input input").setValue(String.valueOf(amount));
            $("[data-test-id='from'].card-input input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
            $("[data-test-id='from'].card-input input").setValue(getCard(card2));
            $("[class='button__text'].button__text").click();
            $("[data-test-id='action-reload'].button").shouldBe(Condition.visible).click();

        }
    }

    @Value
    public static class Registration {
        private String login;
        private String password;
        private String verificationCode;

        public static Registration getUser() {
            return new Registration("vasya", "qwerty123", "12345");
        }

    }
}
