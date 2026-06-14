package org.magic.api.ast.effects.parsers;

import org.magic.api.ast.effects.PTChangeEffect;
import org.magic.api.ast.interfaces.EffectNode;
import org.magic.api.ast.interfaces.parsers.AbstractParser;
import org.magic.api.ast.interfaces.parsers.EffectParser;
import org.magic.api.ast.selectors.factory.SelectorFactory;
import org.magic.api.ast.util.DurationParser;

public class PtModificationParser extends AbstractParser<EffectNode> implements EffectParser {

	@Override
	protected String getPattern() {
		return "^(.*?)\\s+gets\\s+([+-]?(?:\\d+|X))\\/([+-]?(?:\\d+|X))(?:\\s+and\\s+.*?)?\\s+until\\s+(.+?)[\\.]?$";
	}

	@Override
	public EffectNode parse(String text) {
		var matcher = match(text);		
		return new PTChangeEffect(text, SelectorFactory.INSTANCE.parse(matcher.group(1)), matcher.group(2), matcher.group(3), DurationParser.parse(matcher.group(4)));
	}

}
