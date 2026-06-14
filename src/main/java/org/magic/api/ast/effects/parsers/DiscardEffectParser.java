package org.magic.api.ast.effects.parsers;

import org.magic.api.ast.effects.DiscardCardsEffect;
import org.magic.api.ast.interfaces.EffectNode;
import org.magic.api.ast.interfaces.SelectorNode;
import org.magic.api.ast.interfaces.parsers.AbstractParser;
import org.magic.api.ast.interfaces.parsers.EffectParser;
import org.magic.api.ast.selectors.PlayerSelector;
import org.magic.api.ast.selectors.factory.SelectorFactory;
import org.magic.api.ast.util.AmountParser;

public class DiscardEffectParser extends AbstractParser<EffectNode> implements EffectParser {
	
	
	@Override
	protected String getPattern() {
		return 	"^(You |Each opponent |Target player |Each player )?discards?\\s+(a|an|one|two|three|four|five|six|seven|eight|nine|ten|\\\\d+)\\s+cards?[.!]?";
	}

	@Override
	public EffectNode parse(String text) {

		var matcher = match(text);

		SelectorNode selector = new PlayerSelector(false);
		
		if(matcher.group(1)!=null)
			selector = SelectorFactory.INSTANCE.parse(matcher.group(1).trim());
		
		return new DiscardCardsEffect(text, selector,
				AmountParser.parse(matcher.group(2)));
	}
}
