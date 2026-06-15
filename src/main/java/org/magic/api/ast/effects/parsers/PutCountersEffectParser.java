package org.magic.api.ast.effects.parsers;

import org.magic.api.ast.effects.PutCountersEffect;
import org.magic.api.ast.factories.SelectorFactory;
import org.magic.api.ast.interfaces.EffectNode;
import org.magic.api.ast.interfaces.parsers.AbstractParser;
import org.magic.api.ast.interfaces.parsers.EffectParser;
import org.magic.api.ast.util.AmountParser;

public class PutCountersEffectParser extends AbstractParser<EffectNode>  implements EffectParser {

	@Override
	protected String getPattern()
	{
		return "^Put\\s+(a|an|one|two|three|four|five|six|seven|eight|nine|ten|\\d+)\\s+(.+?)\\s+counters?\\s+on\\s+(.+)$";
	}

	@Override
	public EffectNode parse(String text) {

		var matcher = match(text);

		return new PutCountersEffect(text, AmountParser.parse(matcher.group(1)), matcher.group(2).trim(),
				SelectorFactory.INSTANCE.parse(matcher.group(3).trim()));
	}
}
