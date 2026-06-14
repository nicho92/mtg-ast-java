package org.magic.api.ast.effects.parsers;

import org.magic.api.ast.effects.DealDamageEffect;
import org.magic.api.ast.interfaces.EffectNode;
import org.magic.api.ast.interfaces.parsers.AbstractParser;
import org.magic.api.ast.interfaces.parsers.EffectParser;
import org.magic.api.ast.selectors.factory.SelectorFactory;
import org.magic.api.ast.util.AmountParser;

public class DamageEffectParser extends AbstractParser<EffectNode> implements EffectParser {

	@Override
	protected String getPattern() {
		return "^(.+?)\\s+deals?\\s+(\\d+)\\s+damage\\s+to\\s+(.+?)\\.?$";
	}
	
	@Override
	public EffectNode parse(String text) {

		var matcher = match(text);

		return new DealDamageEffect(text, SelectorFactory.INSTANCE.parse(matcher.group(1).trim()), AmountParser.parse(matcher.group(2)),
				SelectorFactory.INSTANCE.parse(matcher.group(3).trim()));
	}
}