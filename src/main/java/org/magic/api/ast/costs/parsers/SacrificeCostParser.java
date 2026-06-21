package org.magic.api.ast.costs.parsers;

import java.util.List;

import org.magic.api.ast.costs.SacrificeCost;
import org.magic.api.ast.factories.SelectorFactory;
import org.magic.api.ast.interfaces.CostNode;
import org.magic.api.ast.interfaces.parsers.AbstractParser;
import org.magic.api.ast.interfaces.parsers.CostParser;
import org.magic.api.ast.util.AmountParser;

public class SacrificeCostParser extends AbstractParser<List<CostNode>> implements CostParser {

	@Override
	public List<CostNode> parse(String text) {
		var matcher = match(text);


		return List.of(new SacrificeCost(text,SelectorFactory.INSTANCE.parse(matcher.group(2).trim()),
				AmountParser.parse(matcher.group(1).trim())));
	}

	@Override
	protected String getPattern() {
		return "^Sacrifice\\s+(a|an|another|one|two|three)\\s+(.+)$";
	}

}
