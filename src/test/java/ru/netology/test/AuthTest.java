package ru.netology.test;

import org.junit.jupiter.api.*;
import ru.netology.data.DataGenerator;

import static com.codeborne.selenide.Configuration.browser;
import static com.codeborne.selenide.Configuration.timeout;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;


class AuthTest {

    @BeforeEach
    void setup() {
        browser = "firefox";
        open("http://localhost:9999");
        timeout = 1000;
        var x = DataGenerator.Registration.getUser();
        DataGenerator.PageElements.loginUser(x);

    }

    @Test
    @DisplayName("Transfer from 1st card to 2nd of the total amount")
    void shouldSuccessfulTransfer() {
        String card1 = "'92df3f1c-a033-48e6-8390-206f6b1f56c0'";
        String card2 = "'0f3f5c2a-249e-4c3d-8287-09f7a039391d'";

        DataGenerator.PageElements.transferToCard(card1, card2, 6000);
        DataGenerator.PageElements.transferToCard(card2, card1, 6000);


        assertEquals(DataGenerator.PageElements.getCardBalance(card1), DataGenerator.PageElements.getCardBalance(card2));
    }

    @Test
    @DisplayName("Transfer from 1st card to 2nd of the total amount, then balance sheet")
    void shouldSuccessfulTransfer2() {
        String card1 = "'92df3f1c-a033-48e6-8390-206f6b1f56c0'";
        String card2 = "'0f3f5c2a-249e-4c3d-8287-09f7a039391d'";

        DataGenerator.PageElements.transferToCard(card1, card2, 6000);
        DataGenerator.PageElements.transferToCard(card1, card2, 4000);

        assertEquals(20_000, DataGenerator.PageElements.getCardBalance(card1));
        assertEquals(0, DataGenerator.PageElements.getCardBalance(card2));

        DataGenerator.PageElements.transferToCard(card2, card1, 10_000);

        assertEquals(DataGenerator.PageElements.getCardBalance(card1), DataGenerator.PageElements.getCardBalance(card2));
    }

    @Test
    @DisplayName("Transfer from the 1st card to the 2nd with an overdraft")
    void OverdraftTest() {
        String card1 = "'92df3f1c-a033-48e6-8390-206f6b1f56c0'";
        String card2 = "'0f3f5c2a-249e-4c3d-8287-09f7a039391d'";

        DataGenerator.PageElements.transferToCard(card1, card2, 6000);
        DataGenerator.PageElements.transferToCard(card1, card2, 4000);
        DataGenerator.PageElements.transferToCard(card1, card2, 6000);

        assertEquals(20_000, DataGenerator.PageElements.getCardBalance(card1));
    }
}