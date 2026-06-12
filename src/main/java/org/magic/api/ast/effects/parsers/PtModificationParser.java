package org.magic.api.ast.effects.parsers;

import java.util.regex.Pattern;

import org.magic.api.ast.effects.PTChangeEffect;
import org.magic.api.ast.interfaces.EffectNode;
import org.magic.api.ast.interfaces.parsers.EffectParser;
import org.magic.api.ast.selectors.factory.SelectorFactory;
import org.magic.api.ast.util.DurationParser;

public class PtModificationParser implements EffectParser {

	 private static final Pattern PATTERN =
	            Pattern.compile(
	                    "^(.*?)\\s+gets\\s+([+-]?(?:\\d+|X))\\/([+-]?(?:\\d+|X))(?:\\s+and\\s+.*?)?\\s+until\\s+(.+?)[\\.]?$",
	                    Pattern.CASE_INSENSITIVE
	            );
	
		@Override
	public boolean supports(String text) {
		return PATTERN.matcher(text).find();
	}

	@Override
	public EffectNode parse(String text) {
		var matcher = PATTERN.matcher(text);

		matcher.find();
		
		return new PTChangeEffect(text, SelectorFactory.INSTANCE.parse(matcher.group(1)), matcher.group(2), matcher.group(3), DurationParser.parse(matcher.group(4)));
	}

}
