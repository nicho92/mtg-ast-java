package org.magic.api.ast.abilities.parsers;

import org.magic.api.ast.abilities.PlaneswalkerAbility;
import org.magic.api.ast.interfaces.AbilityNode;
import org.magic.api.ast.interfaces.parsers.AbilityParser;
import org.magic.api.ast.interfaces.parsers.AbstractParser;
import org.magic.api.ast.util.EffectSequencerSplitter;

public class PlaneswalkerAbilityParser extends AbstractParser<AbilityNode> implements  AbilityParser {

	@Override
	protected String getPattern() {
		return "^\\[([+\\-−]?(?:\\d+|X))\\]\\s*:\\s*(.+)$";
	}
	
	@Override
	public AbilityNode parse(String text) {

		var matcher = match(text);

		return new PlaneswalkerAbility(text,matcher.group(1),EffectSequencerSplitter.INSTANCE.parse(matcher.group(2)));
	}
}
