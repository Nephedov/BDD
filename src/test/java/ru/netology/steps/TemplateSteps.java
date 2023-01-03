package ru.netology.steps;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.ru.*;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TemplateSteps {
    private DashboardPage dashboardPage;

    @Пусть("Пользователь залогинен с именем {string} и паролем {string}")
    public void validAuth(String login, String password) {
        var loginPage = Selenide.open("http://localhost:9999/", LoginPage.class);
        var verificationPage = loginPage.validLogIn(login, password);
        dashboardPage = verificationPage.validVerify(DataHelper.getVerificationCode());

    }

    @Когда("Пользователь переводит {} рублей с карты с номером {} на свою {} карту с главной страницы")
    public void validTransaction(String amount, String cardNumberFrom, int cardIndexTo) {
        var transactionPage = dashboardPage.selectCardToTransfer(cardIndexTo);
        dashboardPage = transactionPage.makeValidTransfer(amount, cardNumberFrom);
    }

    @Тогда("Баланс его {} карты из списка на главной странице должен стать {} рублей")
    public void getFirstCardBalance(int cardIndexBalance, String expectedBalance) {
        var actual = dashboardPage.getCardBalance(cardIndexBalance);
        var expected = Integer.parseInt(expectedBalance.replaceAll(" ", ""));

        assertEquals(expected, actual);
    }

}