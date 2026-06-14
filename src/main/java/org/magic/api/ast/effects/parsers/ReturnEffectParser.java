package org.magic.api.ast.effects.parsers;

import org.magic.api.ast.effects.ReturnEffect;
import org.magic.api.ast.interfaces.EffectNode;
import org.magic.api.ast.interfaces.parsers.AbstractParser;
import org.magic.api.ast.interfaces.parsers.EffectParser;
import org.magic.api.ast.selectors.factory.SelectorFactory;

public class ReturnEffectParser extends AbstractParser<EffectNode>  implements EffectParser {

	@Override
	public EffectNode parse(String text) {

		var matcher = match(text);

		return new ReturnEffect(text, SelectorFactory.INSTANCE.parse(matcher.group(1).trim()), matcher.group(2).trim());
	}

	@Override
	protected String getPattern() {
		return "^Return\\s+(.+?)\\s+to\\s+(.+)$";
	}
}
