package org.magic.api.ast.effects.parsers;

import org.magic.api.ast.effects.GetEmblemEffect;
import org.magic.api.ast.interfaces.EffectNode;
import org.magic.api.ast.interfaces.parsers.AbstractParser;
import org.magic.api.ast.interfaces.parsers.EffectParser;

public class GetEmblemEffectParser  extends AbstractParser<EffectNode> implements EffectParser {

	@Override
	protected String getPattern() {
		return "^You get an emblem with \"(.*?)\"[.!]?$";
	}
	
	@Override
	public EffectNode parse(String text) {

		var matcher = match(text);
		return new GetEmblemEffect(text, matcher.group(1));
	}
}