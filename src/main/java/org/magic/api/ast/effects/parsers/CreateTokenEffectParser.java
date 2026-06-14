package org.magic.api.ast.effects.parsers;

import org.magic.api.ast.effects.CreateTokenEffect;
import org.magic.api.ast.interfaces.EffectNode;
import org.magic.api.ast.interfaces.parsers.AbstractParser;
import org.magic.api.ast.interfaces.parsers.EffectParser;
import org.magic.api.ast.util.AmountParser;

public class CreateTokenEffectParser extends AbstractParser<EffectNode> implements EffectParser {

	@Override
	protected String getPattern() {
		return "Create (.*?) (.*?) tokens?[.!]?";
	}
	
	@Override
	public EffectNode parse(String text) {

		var matcher = match(text);
		return new CreateTokenEffect(text,matcher.group(2),AmountParser.parse(matcher.group(1).trim()));
	}
}