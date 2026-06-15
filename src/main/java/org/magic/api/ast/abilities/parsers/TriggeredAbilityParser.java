package org.magic.api.ast.abilities.parsers;

import org.magic.api.ast.abilities.TriggeredAbility;
import org.magic.api.ast.factories.TriggerFactory;
import org.magic.api.ast.interfaces.AbilityNode;
import org.magic.api.ast.interfaces.parsers.AbilityParser;
import org.magic.api.ast.interfaces.parsers.AbstractParser;
import org.magic.api.ast.util.EffectSequencerSplitter;

public class TriggeredAbilityParser extends AbstractParser<AbilityNode> implements AbilityParser {

	@Override
	protected String getPattern() {
		return "^(When|Whenever|At)\\s+(.*?),\\s*(.*)$";
	}	
	
	@Override
	public AbilityNode parse(String text) {

		var matcher = match(text);
		var trigger = TriggerFactory.INSTANCE.parse(matcher.group(2));
		return new TriggeredAbility(text,trigger, EffectSequencerSplitter.INSTANCE.parse(matcher.group(3)));
	}
}