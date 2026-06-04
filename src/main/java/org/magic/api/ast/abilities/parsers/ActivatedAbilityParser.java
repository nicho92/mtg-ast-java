package org.magic.api.ast.abilities.parsers;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.magic.api.ast.abilities.AbilityNode;
import org.magic.api.ast.abilities.ActivatedAbility;
import org.magic.api.ast.costs.CostNode;
import org.magic.api.ast.effects.parsers.EffectSequenceParser;
import org.magic.api.ast.factories.CostFactory;
import org.magic.api.ast.interfaces.AbilityParser;

public class ActivatedAbilityParser implements AbilityParser {

	private static final Pattern PATTERN = Pattern.compile("^(.*?):\\s*(.*)$");

	@Override
	public boolean supports(String text) {
		return PATTERN.matcher(text).matches();
	}

	@Override
	public AbilityNode parse(String text) {

		Matcher matcher = PATTERN.matcher(text);

		matcher.find();

		String costPart = matcher.group(1);

		String effectPart = matcher.group(2);

		List<CostNode> costs = new CostFactory().parse(costPart);

		return new ActivatedAbility(
				costs,
				new EffectSequenceParser().parse(effectPart)
		);
	}
}