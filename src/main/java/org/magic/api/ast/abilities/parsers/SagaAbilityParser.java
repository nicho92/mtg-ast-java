package org.magic.api.ast.abilities.parsers;

import org.magic.api.ast.abilities.SagaAbility;
import org.magic.api.ast.interfaces.AbilityNode;
import org.magic.api.ast.interfaces.parsers.AbilityParser;
import org.magic.api.ast.interfaces.parsers.AbstractParser;
import org.magic.api.ast.util.AmountParser;
import org.magic.api.ast.util.EffectSequencerSplitter;

public class SagaAbilityParser extends AbstractParser<AbilityNode> implements AbilityParser {


	@Override
	protected String getPattern() {
		return "^(I|II|III|IV|V|VI)\\s+[—-]\\s+(.*)$";
	}
	
	@Override
	public AbilityNode parse(String text) {

		var matcher = match(text);
		var triggerText = matcher.group(2);

		return new SagaAbility(text,AmountParser.parse(matcher.group(1)),EffectSequencerSplitter.INSTANCE.parse(triggerText));
	}

}