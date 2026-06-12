package org.magic.api.ast.effects.parsers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.magic.api.ast.effects.SacrificeEffect;
import org.magic.api.ast.interfaces.EffectNode;
import org.magic.api.ast.interfaces.parsers.EffectParser;
import org.magic.api.ast.selectors.factory.SelectorFactory;

public class SacrificeEffectParser implements EffectParser {

	private static final Pattern PATTERN = Pattern.compile(
			"^(You|Each opponent|Target player|Each player)\\s+sacrifices?\\s+(.+)$", Pattern.CASE_INSENSITIVE);

	@Override
	public boolean supports(String text) {
		return PATTERN.matcher(text).find();
	}

	@Override
	public EffectNode parse(String text) {

		Matcher matcher = PATTERN.matcher(text);

		matcher.find();

		return new SacrificeEffect(text, SelectorFactory.INSTANCE.parse(matcher.group(1).trim()),
				SelectorFactory.INSTANCE.parse(matcher.group(2).trim()));
	}
}
