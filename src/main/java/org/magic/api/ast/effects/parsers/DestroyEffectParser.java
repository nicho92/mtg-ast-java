package org.magic.api.ast.effects.parsers;

import org.magic.api.ast.effects.DestroyTargetEffect;
import org.magic.api.ast.interfaces.EffectNode;
import org.magic.api.ast.interfaces.parsers.AbstractParser;
import org.magic.api.ast.interfaces.parsers.EffectParser;
import org.magic.api.ast.selectors.factory.SelectorFactory;

public class DestroyEffectParser extends AbstractParser<EffectNode> implements EffectParser {

	@Override
	protected String getPattern() {
		return "^Destroy\\s+(.+)";
	}

	@Override
	public EffectNode parse(String text) {

		var matcher = match(text);

		return new DestroyTargetEffect(text, SelectorFactory.INSTANCE.parse(matcher.group(1).trim()));
	}
}