package org.magic.api.ast.abilities.parsers;

import java.util.regex.Pattern;

import org.magic.api.ast.abilities.ReplacementEffectAbility;
import org.magic.api.ast.interfaces.AbilityNode;
import org.magic.api.ast.interfaces.parsers.AbilityParser;
import org.magic.api.ast.util.EffectSequencerSplitter;

public class ReplacementEffectParser implements AbilityParser {

	private static final Pattern PATTERN = Pattern.compile("^If\\s+(.+?\\s+would\\s+.+?),\\s+(.+?)\\s+instead\\.?$",
			Pattern.CASE_INSENSITIVE);

	@Override
	public boolean supports(String text) {
		return PATTERN.matcher(text).matches();
	}

	@Override
	public AbilityNode parse(String text) {

		var matcher = PATTERN.matcher(text);

		matcher.find();

		return new ReplacementEffectAbility(text,matcher.group(1).trim(),EffectSequencerSplitter.INSTANCE.parse(matcher.group(2).trim()));
	}
}
