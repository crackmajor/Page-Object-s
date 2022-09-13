package ru.netology.data;

import lombok.Value;

import java.util.HashMap;
import java.util.Map;

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
        private static Map<String, String> cards = new HashMap<>() {{
            put("'5559 0000 0000 0001'", "'92df3f1c-a033-48e6-8390-206f6b1f56c0'");
            put("'5559 0000 0000 0002'", "'0f3f5c2a-249e-4c3d-8287-09f7a039391d'");
        }};

        public static String getCard(String id) {
            return cards.get(id);
        }
    }
}
