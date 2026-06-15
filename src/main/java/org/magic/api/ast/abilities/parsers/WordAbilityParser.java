package org.magic.api.ast.abilities.parsers;

import org.magic.api.ast.abilities.WordAbility;
import org.magic.api.ast.factories.AbilitiesFactory;
import org.magic.api.ast.interfaces.AbilityNode;
import org.magic.api.ast.interfaces.parsers.AbilityParser;
import org.magic.api.ast.interfaces.parsers.AbstractParser;

public class WordAbilityParser extends AbstractParser<AbilityNode> implements AbilityParser {

	
	@Override
	protected String getPattern() {
		return "^([A-Z][a-zA-Z0-9]*(?:\\s+[a-zA-Z0-9]+){0,2})\\s*[\u2014\u2013-]\\s*(.*)$";
	}
	
	
	@Override
	public AbilityNode parse(String text) {

		var matcher = match(text);

		var abilityWord = matcher.group(1);
		var innerText = matcher.group(2);

		var innerAbility = AbilitiesFactory.INSTANCE.parse(innerText).get(0);

		return new WordAbility(text,abilityWord, innerAbility);
	}
}
