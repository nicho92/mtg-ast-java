package org.magic.api.ast.abilities.parsers;

import java.util.regex.Pattern;

import org.magic.api.ast.abilities.PlaneswalkerAbility;
import org.magic.api.ast.interfaces.AbilityNode;
import org.magic.api.ast.interfaces.parsers.AbilityParser;
import org.magic.api.ast.util.EffectSequencerSplitter;

public class PlaneswalkerAbilityParser implements AbilityParser {

	private static final Pattern PATTERN =Pattern.compile(
            "^\\[([+\\-−]?(?:\\d+|X))\\]\\s*:\\s*(.+)$",
            Pattern.DOTALL
    );

	@Override
	public boolean supports(String text) {
		return PATTERN.matcher(text).matches();
	}

	@Override
	public AbilityNode parse(String text) {

		var matcher = PATTERN.matcher(text);

		matcher.find();

		var loyaltyText = matcher.group(1);
		var effectsText = matcher.group(2);

		return new PlaneswalkerAbility(text,loyaltyText,EffectSequencerSplitter.INSTANCE.parse(effectsText));
	}
}
