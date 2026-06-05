package org.magic.api.ast.abilities.parsers;

import java.util.regex.Pattern;

import org.magic.api.ast.abilities.AbilityNode;
import org.magic.api.ast.abilities.SagaAbility;
import org.magic.api.ast.effects.parsers.EffectSequenceParser;
import org.magic.api.ast.interfaces.AbilityParser;
import org.magic.api.ast.util.AmountParser;

public class SagaAbilityParser implements AbilityParser {

	private static final Pattern PATTERN = Pattern.compile("^(I|II|III|IV|V|VI)\\s+[—-]\\s+(.*)$", Pattern.CASE_INSENSITIVE);

	@Override
	public boolean supports(String text) {
		return PATTERN.matcher(text).matches();
	}

	@Override
	public AbilityNode parse(String text) {

		var matcher = PATTERN.matcher(text);

		matcher.find();

		var triggerText = matcher.group(2);

		return new SagaAbility(
				AmountParser.parse(matcher.group(1)),
				new EffectSequenceParser().parse(triggerText)
		);
	}
	
}