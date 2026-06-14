package org.magic.api.ast.effects.parsers;

import org.magic.api.ast.costs.parsers.ManaCostParser;
import org.magic.api.ast.effects.AddManaEffect;
import org.magic.api.ast.interfaces.EffectNode;
import org.magic.api.ast.interfaces.parsers.AbstractParser;
import org.magic.api.ast.interfaces.parsers.EffectParser;

public class AddManaEffectParser extends AbstractParser<EffectNode> implements EffectParser {

	
	@Override
	protected String getPattern() {
		return "^Add\\s+(.+)$";
	}
	
	@Override
	public EffectNode parse(String text) {

		var matcher = match(text);

		return new AddManaEffect(text,new ManaCostParser().parse(matcher.group(1)));
	}
}