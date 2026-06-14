package org.magic.api.ast.effects.parsers;

import org.magic.api.ast.effects.SacrificeEffect;
import org.magic.api.ast.interfaces.EffectNode;
import org.magic.api.ast.interfaces.parsers.AbstractParser;
import org.magic.api.ast.interfaces.parsers.EffectParser;
import org.magic.api.ast.selectors.factory.SelectorFactory;

public class SacrificeEffectParser extends AbstractParser<EffectNode> implements EffectParser {

	@Override
	protected String getPattern() {
		return "^(You|Each opponent|Target player|Each player)\\s+sacrifices?\\s+(.+)$";
	}
			
	@Override
	public EffectNode parse(String text) {

		var matcher = match(text);

		return new SacrificeEffect(text, SelectorFactory.INSTANCE.parse(matcher.group(1).trim()),
				SelectorFactory.INSTANCE.parse(matcher.group(2).trim()));
	}
}
