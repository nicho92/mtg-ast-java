package org.magic.api.ast.effects.parsers;

import org.magic.api.ast.effects.GainLifeEffect;
import org.magic.api.ast.interfaces.EffectNode;
import org.magic.api.ast.interfaces.parsers.AbstractParser;
import org.magic.api.ast.interfaces.parsers.EffectParser;
import org.magic.api.ast.util.AmountParser;

public class GainLifeEffectParser extends AbstractParser<EffectNode> implements EffectParser {

	@Override
	protected String getPattern() {
		return "^(?:You\\s+)?Gain\\s+(\\d+)\\s+life(.+)";
	}
	
	@Override
	public EffectNode parse(String text) {

		var matcher = match(text);

		return new GainLifeEffect(text, AmountParser.parse(matcher.group(1)));
	}
}