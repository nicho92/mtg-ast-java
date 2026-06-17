package org.magic.api.ast.util;

public final class AmountParser {

	private AmountParser() {
	}

	public static int parse(String text) {

		return switch (text.trim().toLowerCase()) {
		case "a", "an", "one", "another", "i" -> 1;
		case "two", "ii" -> 2;
		case "three", "iii" -> 3;
		case "four", "iv" -> 4;
		case "five", "v" -> 5;
		case "six", "vi" -> 6;
		case "seven", "vii" -> 7;
		case "eight","viii" -> 8;
		case "nine","ix" -> 9;
		case "ten" -> 10;
		case "eleven" -> 11;
		case "twelve" -> 12;
		case "thirteen" -> 13;
		case "fourteen" -> 14;
		case "that", "many" -> 1;
		default -> Integer.parseInt(text);
		};
	}
}
