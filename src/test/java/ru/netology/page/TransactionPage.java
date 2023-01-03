package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TransactionPage {
    private SelenideElement amountInput = $("[data-test-id='amount'] input");
    private SelenideElement fromInput = $("[data-test-id='from'] input");
    private SelenideElement transferHead = $(byText("Пополнение карты"));
    private SelenideElement transferButton = $("button[data-test-id='action-transfer']");
    private SelenideElement errorMessage = $("[data-test-id='error-message']");

    public DashboardPage makeValidTransfer(String amountToTransfer, String cardNumber) {
        makeTransfer(amountToTransfer, cardNumber);
        return new DashboardPage();
    }

    public void makeTransfer(String amountToTransfer, String cardNumber) {
        amountInput.setValue(amountToTransfer);
        fromInput.setValue(cardNumber);
        transferButton.click();
    }

    public void findErrorMessage(String expectedText) {
        errorMessage.shouldHave(exactText(expectedText), Duration.ofSeconds(15)).shouldBe(visible);
    }

}
