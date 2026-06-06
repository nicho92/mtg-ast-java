package org.magic.api.ast.abilities.parsers;

import java.util.regex.Pattern;

import org.magic.api.ast.abilities.AbilityNode;
import org.magic.api.ast.abilities.PlaneswalkerAbility;
import org.magic.api.ast.effects.parsers.EffectSequenceParser;

public class PlaneswalkerAbilityParser implements AbilityParser {

	private static final Pattern PATTERN = Pattern.compile("^([+\u2212-]?(\\d+|X)):\\s*(.*)$", Pattern.CASE_INSENSITIVE);

	@Override
	public boolean supports(String text) {
		return PATTERN.matcher(text).matches();
	}

	@Override
	public AbilityNode parse(String text) {

		var matcher = PATTERN.matcher(text);

		matcher.find();

		var loyaltyText = matcher.group(1);
		var effectsText = matcher.group(3);

		return new PlaneswalkerAbility(
				loyaltyText,
				new EffectSequenceParser().parse(effectsText)
		);
	}
}
