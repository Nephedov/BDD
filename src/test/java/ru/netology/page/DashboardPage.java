package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Selenide.*;

public class DashboardPage {

    private ElementsCollection cards = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";
    public static SelenideElement depositFirstCardButton = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0'] button");

    public static SelenideElement depositSecondCardButton = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d'] button");

    private final SelenideElement heading = $("[data-test-id='dashboard']");

    public void verifyIsDashboardPage() {
        heading.shouldBe(Condition.visible).shouldHave(Condition.text("Личный кабинет"));
    }

    public String getFirstCardBalance() {
        val text = cards.first().text();
        return extractBalance(text);
    }

    public String getSecondCardBalance() {
        val text = cards.last().text();
        return extractBalance(text);
    }


    private String extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return value;
    }
}

