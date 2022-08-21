package ru.netology.data;

import lombok.Value;

public class DataGenerator {
    @Value
    public static class Registration {
        private String login;
        private String password;
        private String verificationCode;

        public static Registration getUser() {
            return new Registration("vasya", "qwerty123", "12345");
        }
    }
}
