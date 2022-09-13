package ru.netology.page;

import com.codeborne.selenide.Condition;
import ru.netology.data.DataGenerator;

public class VerificationPage {
    public DashboardPage verificationPage(DataGenerator.VerificationCode verificationCode) {
        PageElements.getField("verificationForm").shouldBe(Condition.visible);
        PageElements.getField("verificationForm").setValue(verificationCode.getCode());
        PageElements.getField("verificationButton").click();
        return new DashboardPage();
    }
}
