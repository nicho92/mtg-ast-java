package org.magic.api.ast.abilities.parsers;

import java.util.regex.Pattern;

import org.magic.api.ast.abilities.ContinuousModifierAbility;
import org.magic.api.ast.interfaces.AbilityNode;
import org.magic.api.ast.interfaces.parsers.AbilityParser;
import org.magic.api.ast.modifiers.PowerToughnessModifier;
import org.magic.api.ast.selectors.factory.SelectorFactory;

public class ContinuousModifierAbilityParser implements AbilityParser {

	private static final Pattern PATTERN = Pattern.compile("^(.+?)\\s+gets?\\s+([+-]\\d+)/([+-]\\d+)\\.?$",
			Pattern.CASE_INSENSITIVE);

	@Override
	public boolean supports(String text) {
		return PATTERN.matcher(text).matches();
	}

	@Override
	public AbilityNode parse(String text) {

		var matcher = PATTERN.matcher(text);

		matcher.find();

		return new ContinuousModifierAbility(text,SelectorFactory.INSTANCE.parse(matcher.group(1).trim()),
				new PowerToughnessModifier(Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(3))));
	}
}
