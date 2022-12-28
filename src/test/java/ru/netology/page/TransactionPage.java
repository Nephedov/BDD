package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.Cards;

import static com.codeborne.selenide.Selenide.$;
import static ru.netology.page.DashboardPage.*;

public class TransactionPage {
    private SelenideElement amountField = $("[data-test-id='amount'] input");
    private SelenideElement cardNumberField = $("[data-test-id='from'] input");
    private SelenideElement confirmButton = $("button[data-test-id='action-transfer']");
    private SelenideElement reloadButton = $("button[data-test-id='action-reload']");

    private SelenideElement errorNotification = $("[data-test-id='error-notification']");

    public DashboardPage toSecondCardValidTransaction(String cash, String cardNumber) {

        depositSecondCardButton.click();
        amountField.setValue(cash);
        cardNumberField.setValue(String.valueOf(cardNumber));
        confirmButton.click();
        reloadButton.click();
        return new DashboardPage();
    }

    public DashboardPage toFirstCardValidTransaction(String cash, String cardNumber) {

        depositFirstCardButton.click();
        amountField.setValue(cash);
        cardNumberField.setValue(String.valueOf(cardNumber));
        confirmButton.click();
        reloadButton.click();
        return new DashboardPage();
    }

    public TransactionPage fromFirstToSecondCardInvalidTransaction(int cash) {
        String amount = String.valueOf(cash);

        depositSecondCardButton.click();
        amountField.setValue(amount);
        cardNumberField.setValue(new Cards().firstCard().getNumber());
        confirmButton.click();

        errorNotification.shouldBe(Condition.visible);
        return this;
    }

}
