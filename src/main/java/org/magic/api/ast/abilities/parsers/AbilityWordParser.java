package org.magic.api.ast.abilities.parsers;

import java.util.regex.Pattern;

import org.magic.api.ast.abilities.AbilityNode;
import org.magic.api.ast.abilities.AbilityWordAbility;
import org.magic.api.ast.engine.OracleParser;
import org.magic.api.ast.interfaces.AbilityParser;

public class AbilityWordParser implements AbilityParser {

	private static final Pattern PATTERN = Pattern.compile("^([A-Z][a-zA-Z0-9]*(?:\\s+[a-zA-Z0-9]+){0,2})\\s*[\u2014\u2013-]\\s*(.*)$");

	
	@Override
	public boolean supports(String text) {
		return PATTERN.matcher(text).matches();
	}

	@Override
	public AbilityNode parse(String text) {

		var matcher = PATTERN.matcher(text);

		matcher.find();

		var abilityWord = matcher.group(1);
		var innerText = matcher.group(2);

		var innerAbility = new OracleParser().parse("", innerText).getAbilities().get(0);

		return new AbilityWordAbility(
				abilityWord,
				innerAbility
		);
	}
}
