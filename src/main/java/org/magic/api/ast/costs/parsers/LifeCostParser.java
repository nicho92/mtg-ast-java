package org.magic.api.ast.costs.parsers;

import java.util.List;

import org.magic.api.ast.costs.LifeCost;
import org.magic.api.ast.interfaces.CostNode;
import org.magic.api.ast.interfaces.parsers.AbstractParser;
import org.magic.api.ast.interfaces.parsers.CostParser;
import org.magic.api.ast.util.AmountParser;

public class LifeCostParser extends AbstractParser<List<CostNode>> implements CostParser {

	
	@Override
	protected String getPattern() {
		return "pay (.*?) lifes?";
	}
	
	@Override
	public List<CostNode> parse(String text) {

		var matcher = match(text);

		if (!matcher.matches()) {
			return List.of();
		}

		return List.of(new LifeCost(text,AmountParser.parse(matcher.group(1).trim())));
	}

}
