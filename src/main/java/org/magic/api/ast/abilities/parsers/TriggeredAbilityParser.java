package org.magic.api.ast.abilities.parsers;

import java.util.regex.Pattern;

import org.magic.api.ast.abilities.TriggeredAbility;
import org.magic.api.ast.interfaces.AbilityNode;
import org.magic.api.ast.interfaces.parsers.AbilityParser;
import org.magic.api.ast.triggers.factory.TriggerFactory;
import org.magic.api.ast.util.EffectSequencerSplitter;

public class TriggeredAbilityParser implements AbilityParser {

	private static final Pattern PATTERN = Pattern.compile("^(When|Whenever|At)\\s+(.*?),\\s*(.*)$",
			Pattern.CASE_INSENSITIVE);

	@Override
	public boolean supports(String text) {
		return PATTERN.matcher(text).matches();
	}

	@Override
	public AbilityNode parse(String text) {

		var matcher = PATTERN.matcher(text);
		matcher.find();
		var trigger = TriggerFactory.INSTANCE.parse(matcher.group(2));
		return new TriggeredAbility(text,trigger, EffectSequencerSplitter.INSTANCE.parse(matcher.group(3)));
	}
}