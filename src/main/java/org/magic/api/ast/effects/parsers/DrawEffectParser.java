package org.magic.api.ast.effects.parsers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.magic.api.ast.effects.DrawCardsEffect;
import org.magic.api.ast.effects.EffectNode;
import org.magic.api.ast.interfaces.EffectParser;

public class DrawEffectParser implements EffectParser {

	private static final Pattern PATTERN = Pattern.compile("^Draw\\s+(a|two|three|\\d+)\\s+card",
			Pattern.CASE_INSENSITIVE);

	@Override
	public boolean supports(String text) {
		return PATTERN.matcher(text).find();
	}

	@Override
	public EffectNode parse(String text) {

		Matcher matcher = PATTERN.matcher(text);

		matcher.find();

		String amount = matcher.group(1);

		return new DrawCardsEffect(

				switch (amount) {
				case "a" -> 1;
				case "two" -> 2;
				case "three" -> 3;
				default -> Integer.parseInt(amount);
				}

		);
	}
}