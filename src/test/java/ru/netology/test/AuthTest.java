package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataGenerator;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Configuration.browser;
import static com.codeborne.selenide.Configuration.timeout;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;


class AuthTest {

    @BeforeEach
    void setup() {
        browser = "chrome";
        open("http://localhost:9999");
        timeout = 1000;
    }

    @Test
    @DisplayName("Transfer from 1st card to 2nd of the total amount")
    void shouldSuccessfulTransfer() {
        var user = DataGenerator.Registration.getUser();
        var code = DataGenerator.VerificationCode.getVerificationCode();
        LoginPage loginPage = new LoginPage();

        var cardBalancePage =
                loginPage.login(user)
                        .verificationPage(code);

        var card1InitialBalance = cardBalancePage.getBalance(0);
        var card2InitialBalance = cardBalancePage.getBalance(1);

        cardBalancePage.replenishment(1).from(0, card1InitialBalance);

        var card1FinishBalance = cardBalancePage.getBalance(0);
        var card2FinishBalance = cardBalancePage.getBalance(1);

        assertEquals(card2InitialBalance + card1InitialBalance, card2FinishBalance);
        assertEquals(card1InitialBalance - card1InitialBalance, card1FinishBalance);
    }

    @Test
    @DisplayName("Transfer from 1st card to 2nd of the total amount")
    void shouldSuccessfulTransfer2() {
        var user = DataGenerator.Registration.getUser();
        var code = DataGenerator.VerificationCode.getVerificationCode();
        LoginPage loginPage = new LoginPage();

        var cardBalancePage =
                loginPage.login(user)
                        .verificationPage(code);

        var card1InitialBalance = cardBalancePage.getBalance(0);
        var card2InitialBalance = cardBalancePage.getBalance(1);

        cardBalancePage.replenishment(0).from(1, card1InitialBalance);

        var card1FinishBalance = cardBalancePage.getBalance(0);
        var card2FinishBalance = cardBalancePage.getBalance(1);

        assertEquals(card2InitialBalance + card1InitialBalance, card2FinishBalance);
        assertEquals(card1InitialBalance - card1InitialBalance, card1FinishBalance);
    }

    @Test
    @DisplayName("Transfer from the 1st card to the 2nd with an overdraft")
    void overdraftTest() {
        var user = DataGenerator.Registration.getUser();
        var code = DataGenerator.VerificationCode.getVerificationCode();
        LoginPage loginPage = new LoginPage();

        var cardBalancePage =
                loginPage.login(user)
                        .verificationPage(code);

        var card1InitialBalance = cardBalancePage.getBalance(0);
        var card2InitialBalance = cardBalancePage.getBalance(1);

        cardBalancePage.replenishment(1).from(0, card1InitialBalance + card1InitialBalance);

        var card1FinishBalance = cardBalancePage.getBalance(0);
        var card2FinishBalance = cardBalancePage.getBalance(1);

        assertEquals(card1InitialBalance, card1FinishBalance);
        assertEquals(card2InitialBalance, card2FinishBalance);
    }
}
