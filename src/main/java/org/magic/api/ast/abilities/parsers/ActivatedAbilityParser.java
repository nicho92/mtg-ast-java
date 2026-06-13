package org.magic.api.ast.abilities.parsers;

import java.util.regex.Pattern;

import org.magic.api.ast.abilities.ActivatedAbility;
import org.magic.api.ast.costs.factory.CostFactory;
import org.magic.api.ast.interfaces.AbilityNode;
import org.magic.api.ast.interfaces.parsers.AbilityParser;
import org.magic.api.ast.util.EffectSequencerSplitter;

public class ActivatedAbilityParser implements AbilityParser {

	private static final Pattern PATTERN = Pattern.compile("^(.*?):\\s*(.*)$");

	@Override
	public boolean supports(String text) {
		return PATTERN.matcher(text).matches();
	}

	@Override
	public AbilityNode parse(String text) {

		var matcher = PATTERN.matcher(text);
		matcher.find();
		var costs = CostFactory.INSTANCE.parse(matcher.group(1));
		return new ActivatedAbility(text,costs, EffectSequencerSplitter.INSTANCE.parse(matcher.group(2)));
	}
}