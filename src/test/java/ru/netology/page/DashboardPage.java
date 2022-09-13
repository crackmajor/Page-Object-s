package ru.netology.page;

import com.codeborne.selenide.Condition;
import lombok.val;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;
import static ru.netology.data.DataGenerator.Cards.getCard;

public class DashboardPage {
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

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
