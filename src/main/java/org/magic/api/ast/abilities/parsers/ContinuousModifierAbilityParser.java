package org.magic.api.ast.abilities.parsers;

import org.magic.api.ast.abilities.ContinuousModifierAbility;
import org.magic.api.ast.factories.SelectorFactory;
import org.magic.api.ast.interfaces.AbilityNode;
import org.magic.api.ast.interfaces.parsers.AbilityParser;
import org.magic.api.ast.interfaces.parsers.AbstractParser;
import org.magic.api.ast.modifiers.PowerToughnessModifier;

public class ContinuousModifierAbilityParser extends AbstractParser<AbilityNode> implements AbilityParser {

	
	@Override
	protected String getPattern() {
		return "^(.+?)\\s+gets?\\s+([+-]\\d+)/([+-]\\d+)\\.?$";
	}
	
	@Override
	public AbilityNode parse(String text) {

		var matcher = match(text);

		return new ContinuousModifierAbility(text,SelectorFactory.INSTANCE.parse(matcher.group(1).trim()),new PowerToughnessModifier(Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(3))));
	}
}
