package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import lombok.val;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CardBalancePage {

    private ElementsCollection cards = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";
    private final String cardSelectorStart = "<div data-test-id=\"";
    private final String cardSelectorFinish = "\">**** **** **** ";

    public int getBalance(int id) {
        val text = cards.get(id).text();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public CardReplenishmentPage replenishment(int card) {
        val text = cards.get(card).toString();
        val start = text.indexOf(cardSelectorStart);
        val finish = text.indexOf(cardSelectorFinish);
        val value = text.substring(start + cardSelectorStart.length(), finish);
        $("[data-test-id='action-reload'].button").shouldBe(Condition.visible);
        $("[data-test-id='" + value + "'] button").click();
        return new CardReplenishmentPage();
    }
}
