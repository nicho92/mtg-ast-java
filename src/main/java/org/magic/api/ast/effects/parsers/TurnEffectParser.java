package org.magic.api.ast.effects.parsers;

import org.magic.api.ast.effects.ExtraTurnEffect;
import org.magic.api.ast.interfaces.EffectNode;
import org.magic.api.ast.interfaces.parsers.AbstractParser;
import org.magic.api.ast.interfaces.parsers.EffectParser;
import org.magic.api.ast.selectors.PlayerSelector;
import org.magic.api.ast.util.AmountParser;

public class TurnEffectParser extends AbstractParser<EffectNode> implements EffectParser {

	@Override
	protected String getPattern() {
		return "^take\\s+(an|a|\\d+)\\s+extra\\s+turns?\\s+after\\s+this\\s+one\\.?$";
	}
	
	@Override
	public EffectNode parse(String text) {

		var matcher = match(text);
		return new ExtraTurnEffect(text,AmountParser.parse(matcher.group(1)), new PlayerSelector(text.contains("target")));
	}
}