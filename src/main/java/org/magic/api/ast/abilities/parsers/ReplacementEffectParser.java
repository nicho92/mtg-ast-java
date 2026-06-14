package org.magic.api.ast.abilities.parsers;

import org.magic.api.ast.abilities.ReplacementEffectAbility;
import org.magic.api.ast.interfaces.AbilityNode;
import org.magic.api.ast.interfaces.parsers.AbilityParser;
import org.magic.api.ast.interfaces.parsers.AbstractParser;
import org.magic.api.ast.util.EffectSequencerSplitter;

public class ReplacementEffectParser extends AbstractParser<AbilityNode>  implements AbilityParser {


	@Override
	protected String getPattern() {
		return "^If\\s+(.+?\\s+would\\s+.+?),\\s+(.+?)\\s+instead\\.?$";
	}
	
	@Override
	public AbilityNode parse(String text) {

		var matcher =match(text);

		return new ReplacementEffectAbility(text,matcher.group(1).trim(),EffectSequencerSplitter.INSTANCE.parse(matcher.group(2).trim()));
	}
}
