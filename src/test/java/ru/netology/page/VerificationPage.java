package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private SelenideElement codeField = $("[data-test-id='code'] input");
    private SelenideElement verifyButton = $("[data-test-id='action-verify']");

    private SelenideElement errorNotification = $("[data-test-id='error-notification']");

    public VerificationPage() {

        codeField.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public DashboardPage validVerify(/* DataHelper.VerificationCode verificationCode */) {
        codeField.setValue(DataHelper.getVerificationCode().getCode());
        verifyButton.click();
        return new DashboardPage();
    }

    public DashboardPage validVerify(String verificationCode) {
        codeField.setValue(verificationCode);
        verifyButton.click();
        return new DashboardPage();
    }

    public VerificationPage invalidVerify() {
        codeField.setValue("1234");
        verifyButton.click();
        errorNotification.shouldBe(Condition.visible).shouldHave(Condition.text("Неверно указан код! Попробуйте ещё раз."));

        return this;
    }


}
