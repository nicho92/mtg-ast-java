package org.magic.api.ast.effects.parsers;

import java.util.regex.Pattern;

import org.magic.api.ast.effects.ReturnEffect;
import org.magic.api.ast.interfaces.EffectNode;
import org.magic.api.ast.interfaces.parsers.EffectParser;
import org.magic.api.ast.selectors.factory.SelectorFactory;

public class ReturnEffectParser implements EffectParser {

	private static final Pattern PATTERN = Pattern.compile("^Return\\s+(.+?)\\s+to\\s+(.+)$", Pattern.CASE_INSENSITIVE);

	@Override
	public boolean supports(String text) {
		return PATTERN.matcher(text).find();
	}

	@Override
	public EffectNode parse(String text) {

		var matcher = PATTERN.matcher(text);

		matcher.find();

		return new ReturnEffect(text, SelectorFactory.INSTANCE.parse(matcher.group(1).trim()), matcher.group(2).trim());
	}
}
