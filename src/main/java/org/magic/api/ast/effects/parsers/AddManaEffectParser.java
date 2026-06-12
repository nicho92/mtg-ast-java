package org.magic.api.ast.effects.parsers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.magic.api.ast.costs.parsers.ManaCostParser;
import org.magic.api.ast.effects.AddManaEffect;
import org.magic.api.ast.interfaces.EffectNode;
import org.magic.api.ast.interfaces.parsers.EffectParser;

public class AddManaEffectParser implements EffectParser {

	private static final Pattern PATTERN = Pattern.compile("^Add\\s+(.+)$", Pattern.CASE_INSENSITIVE);

	@Override
	public boolean supports(String text) {
		return PATTERN.matcher(text).find();
	}

	@Override
	public EffectNode parse(String text) {

		Matcher matcher = PATTERN.matcher(text);

		matcher.find();

		return new AddManaEffect(text,new ManaCostParser().parse(matcher.group(1)));
	}
}