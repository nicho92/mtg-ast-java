package org.magic.api.ast.effects.parsers;

import org.magic.api.ast.effects.DrawCardsEffect;
import org.magic.api.ast.interfaces.EffectNode;
import org.magic.api.ast.interfaces.parsers.AbstractParser;
import org.magic.api.ast.interfaces.parsers.EffectParser;
import org.magic.api.ast.util.AmountParser;

public class DrawEffectParser extends AbstractParser<EffectNode>  implements EffectParser {

	@Override
	public EffectNode parse(String text) {
		var matcher = match(text);
		return new DrawCardsEffect(text, AmountParser.parse( matcher.group(1)));
	}

	@Override
	protected String getPattern() {
		return "^Draw\\s+(a|two|three|\\d+)\\s+cards?$";
	}
}