package org.magic.api.ast.effects.parsers;

import org.magic.api.ast.effects.ExileEffect;
import org.magic.api.ast.factories.SelectorFactory;
import org.magic.api.ast.interfaces.EffectNode;
import org.magic.api.ast.interfaces.parsers.AbstractParser;
import org.magic.api.ast.interfaces.parsers.EffectParser;

public class ExileEffectParser extends AbstractParser<EffectNode> implements EffectParser {

	
	@Override
	protected String getPattern() {
		return "^Exile\\s+(.+)$";
	}
	
	@Override
	public EffectNode parse(String text) {

		var matcher = match(text);
		return new ExileEffect(text, SelectorFactory.INSTANCE.parse(matcher.group(1).trim()));
	}
}
