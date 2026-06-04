package org.magic.api.ast.util;

public final class AmountParser {

    private AmountParser() {
    }

    public static int parse(String text) {

        return switch (text.toLowerCase()) {
            case "a", "an", "one" -> 1;
            case "two" -> 2;
            case "three" -> 3;
            case "four" -> 4;
            case "five" -> 5;
            case "six" -> 6;
            case "seven" -> 7;
            case "eight" -> 8;
            case "nine" -> 9;
            case "ten" -> 10;
            default -> Integer.parseInt(text);
        };
    }
}
