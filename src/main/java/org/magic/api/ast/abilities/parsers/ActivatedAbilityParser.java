package org.magic.api.ast.abilities.parsers;

import org.magic.api.ast.abilities.ActivatedAbility;
import org.magic.api.ast.factories.CostFactory;
import org.magic.api.ast.interfaces.AbilityNode;
import org.magic.api.ast.interfaces.parsers.AbilityParser;
import org.magic.api.ast.interfaces.parsers.AbstractParser;
import org.magic.api.ast.util.EffectSequencerSplitter;

public class ActivatedAbilityParser extends AbstractParser<AbilityNode>  implements AbilityParser {

	
	@Override
	protected String getPattern() {
		return "^(.*?):\\s*(.*)$";
	}
	
	@Override
	public AbilityNode parse(String text) {

		var matcher = match(text);
		var costs = CostFactory.INSTANCE.parse(matcher.group(1));
		return new ActivatedAbility(text,costs, EffectSequencerSplitter.INSTANCE.parse(matcher.group(2)));
	}
}