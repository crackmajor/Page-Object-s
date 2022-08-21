package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataGenerator;
import ru.netology.data.PageElements;
import ru.netology.data.PageElements.LoginPage;

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

    }

    @Test
    @DisplayName("Transfer from 1st card to 2nd of the total amount")
    void shouldSuccessfulTransfer() {

        var user = DataGenerator.Registration.getUser();
        LoginPage loginPage = new PageElements().new LoginPage();

        var dashboardPage =
                loginPage.login(user)
                        .verificationPage(user);

        var card1Balance = dashboardPage.getCardBalance(1);
        var card2Balance = dashboardPage.getCardBalance(2);

        dashboardPage.transferToCard(2, 1, card1Balance);

        assertEquals(card2Balance + card1Balance, dashboardPage.getCardBalance(2));
    }

    @Test
    @DisplayName("Transfer from 1st card to 2nd of the total amount")
    void shouldSuccessfulTransfer2() {

        var user = DataGenerator.Registration.getUser();
        LoginPage loginPage = new PageElements().new LoginPage();
        var dashboardPage =
                loginPage.login(user)
                        .verificationPage(user);

        var card1Balance = dashboardPage.getCardBalance(1);
        var card2Balance = dashboardPage.getCardBalance(2);

        dashboardPage.transferToCard(2, 1, card1Balance);

        assertEquals(dashboardPage.getCardBalance(2), card2Balance + card1Balance);

    }

    @Test
    @DisplayName("Transfer from the 1st card to the 2nd with an overdraft")
    void OverdraftTest() {

        var user = DataGenerator.Registration.getUser();
        LoginPage loginPage = new PageElements().new LoginPage();
        var dashboardPage =
                loginPage.login(user)
                        .verificationPage(user);

        var card1Balance = dashboardPage.getCardBalance(1);
        var card2Balance = dashboardPage.getCardBalance(2);

        dashboardPage.transferToCard(1, 2, card2Balance + card2Balance);

        assertEquals(card1Balance, dashboardPage.getCardBalance(1));
    }
}
