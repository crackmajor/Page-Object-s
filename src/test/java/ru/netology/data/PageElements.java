package ru.netology.data;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import org.openqa.selenium.Keys;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PageElements {

    private Map<String, SelenideElement> elementPage = new HashMap<String, SelenideElement>() {{
        put("loginField", $("[name='login'].input__control"));
        put("passwordField", $("[data-test-id='password'].input input.input__control"));
        put("button", $("[data-test-id='action-login'].button"));
        put("verificationForm", $("[data-test-id='code'].input input.input__control"));
        put("verificationButton", $("[data-test-id='action-verify'].button"));
    }};

    public SelenideElement getField(String name) {
        return elementPage.get(name);
    }

    public class LoginPage {

        public VerificationPage login(DataGenerator.Registration user) {
            getField("loginField").setValue(user.getLogin());
            getField("passwordField").setValue(user.getPassword());
            getField("button").click();
            return new VerificationPage();
        }
    }

    public class VerificationPage {

        public DashboardPage verificationPage(DataGenerator.Registration user) {
            getField("verificationForm").shouldBe(Condition.visible);
            getField("verificationForm").setValue(user.getVerificationCode());
            getField("verificationButton").click();
            return new DashboardPage();
        }
    }

    public class DashboardPage {

        private final String balanceStart = "баланс: ";
        private final String balanceFinish = " р.";

        private ElementsCollection cardsList = $$(".list__item div");

        private Map<String, String> cards = new HashMap<String, String>() {{
            put("'5559 0000 0000 0001'", "'92df3f1c-a033-48e6-8390-206f6b1f56c0'");
            put("'5559 0000 0000 0002'", "'0f3f5c2a-249e-4c3d-8287-09f7a039391d'");
        }};

        public String getCard(String id) {
            return cards.get(id);
        }

        public int getCardBalance(int id) {
            val text = $("[data-test-id=" + getCard("'5559 0000 0000 000" + id + "'") + "]").getText();
            return extractBalance(text);
        }

        private int extractBalance(String text) {
            val start = text.indexOf(balanceStart);
            val finish = text.indexOf(balanceFinish);
            val value = text.substring(start + balanceStart.length(), finish);
            return Integer.parseInt(value);
        }

        public DashboardPage transferToCard(int toCard, int fromCard, int amount) {
            String card1 = getCard("'5559 0000 0000 000" + toCard + "'");
            String card2 = "'5559 0000 0000 000" + fromCard + "'";
            $("[data-test-id=" + card1 + "] button").click();
            $("[data-test-id='amount'].input input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
            $("[data-test-id='amount'].input input").setValue(String.valueOf(amount));
            $("[data-test-id='from'].card-input input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
            $("[data-test-id='from'].card-input input").setValue(card2);
            $("[class='button__text'].button__text").click();
            $("[data-test-id='action-reload'].button").shouldBe(Condition.visible).click();
            return new DashboardPage();
        }
    }
}
