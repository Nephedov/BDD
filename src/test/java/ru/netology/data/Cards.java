package ru.netology.data;

import lombok.Value;

public class Cards {

    @Value
    public static class CardInfo {
        private String Number;
    }

    public static CardInfo firstCard() {
        return new CardInfo("5559 0000 0000 0001");
    }

    public static CardInfo secondCard() {
        return new CardInfo("5559 0000 0000 0002");
    }
}
