package ru.netology.page;

import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;
import static ru.netology.data.DataGenerator.Cards.getCardNumber;

public class CardReplenishmentPage {

    public CardBalancePage from(int id, int amount) {
        String value = getCardNumber(id);
        $("[data-test-id='amount'].input input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='amount'].input input").setValue(String.valueOf(amount));
        $("[data-test-id='from'].card-input input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='from'].card-input input").setValue(value);
        $("[class='button__text'].button__text").click();
        return new CardBalancePage();
    }
}
