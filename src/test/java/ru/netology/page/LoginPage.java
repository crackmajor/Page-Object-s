package ru.netology.page;

import ru.netology.data.DataGenerator;

public class LoginPage {
    public VerificationPage login(DataGenerator.Registration user) {
        PageElements.getField("loginField").setValue(user.getLogin());
        PageElements.getField("passwordField").setValue(user.getPassword());
        PageElements.getField("button").click();
        return new VerificationPage();
    }
}
