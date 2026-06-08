package org.magic.api.ast.costs.parsers;

import java.util.List;
import java.util.regex.Pattern;

import org.magic.api.ast.costs.LifeCost;
import org.magic.api.ast.interfaces.CostNode;
import org.magic.api.ast.interfaces.parsers.CostParser;
import org.magic.api.ast.util.AmountParser;

public class LifeCostParser implements CostParser {

	private static final Pattern PATTERN = Pattern.compile("pay (.*?) life",Pattern.CASE_INSENSITIVE);

	@Override
	public boolean supports(String text) {
		return PATTERN.matcher(text).matches();
	}

	@Override
	public List<CostNode> parse(String text) {

		var matcher = PATTERN.matcher(text);

		if (!matcher.matches()) {
			return List.of();
		}

		return List.of(new LifeCost(AmountParser.parse(matcher.group(1).trim())));
	}

}
