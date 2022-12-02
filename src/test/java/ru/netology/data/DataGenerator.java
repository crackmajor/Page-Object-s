package ru.netology.data;

import lombok.Value;

import java.util.*;

public class DataGenerator {
    @Value
    public static class Registration {
        private String login;
        private String password;

        public static Registration getUser() {
            return new Registration("vasya", "qwerty123");
        }
    }

    @Value
    public static class VerificationCode {
        private String code;

        public static VerificationCode getVerificationCode() {
            return new VerificationCode("12345");
        }
    }

    public static class Cards {
        private static List<String> cardsIds = new ArrayList<>() {{
            add(0, "92df3f1c-a033-48e6-8390-206f6b1f56c0");
            add(1, "0f3f5c2a-249e-4c3d-8287-09f7a039391d");
        }};

        private static List<String> cardNumbers = new ArrayList<>() {{
            add(0, "5559 0000 0000 0001");
            add(1, "5559 0000 0000 0002");
        }};

        public static String getId(int id) {
            return cardsIds.get(id);
        }

        public static String getCardNumber(int id) {
            return cardNumbers.get(id);
        }
    }
}
