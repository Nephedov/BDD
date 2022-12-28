package ru.netology.steps;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import org.junit.jupiter.api.Assertions;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;
import ru.netology.page.TransactionPage;
import ru.netology.page.VerificationPage;


public class TemplateSteps {
    private static LoginPage loginPage;
    private static DashboardPage dashboardPage;

    @Пусть("пользователь залогинен с именем {string} и паролем {string}")
    public void validAuth(String login, String password) {
       loginPage = Selenide.open("http://localhost:9999/", LoginPage.class);
       dashboardPage = loginPage.validLogin(login, password).validVerify();
    }

    @Когда("пользователь переводит {string} рублей с карты с номером {string} на свою 1 карту с главной страницы")
    public void validTransaction(String amount, String cardNumber) {
        dashboardPage = new TransactionPage().toFirstCardValidTransaction(amount, cardNumber);
    }

    @Тогда("баланс его 1 карты из списка на главной странице должен стать {string} рублей")
    public void getFirstCardBalance(String cash) {
        String expected = cash.replace(" ", "");
        String actual = dashboardPage.getFirstCardBalance();
        Assertions.assertEquals(expected, actual);
    }

}