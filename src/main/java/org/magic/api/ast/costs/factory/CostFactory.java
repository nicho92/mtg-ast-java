package org.magic.api.ast.costs.factory;

import java.util.ArrayList;
import java.util.List;

import org.magic.api.ast.costs.parsers.LifeCostParser;
import org.magic.api.ast.costs.parsers.ManaCostParser;
import org.magic.api.ast.costs.parsers.SacrificeCostParser;
import org.magic.api.ast.costs.parsers.TapCostParser;
import org.magic.api.ast.interfaces.CostNode;
import org.magic.api.ast.interfaces.parsers.CostParser;

public class CostFactory {

	private final List<CostParser> parsers;
	public static final CostFactory INSTANCE = new CostFactory();

	private CostFactory() {

		this.parsers = List.of(
				new TapCostParser(), 
				new ManaCostParser(), 
				new SacrificeCostParser(),
				new LifeCostParser());
	}

	public List<CostNode> parse(String costText) {

		var result = new ArrayList<CostNode>();
		var parts = costText.split(",");

		for (var part : parts) {
			var normalized = part.trim();
			boolean matched = false;

			for (var parser : parsers) {
				if (parser.supports(normalized)) {
					result.addAll(parser.parse(normalized));
					matched = true;
					break;
				}
			}

			if (!matched) {
				throw new IllegalArgumentException("Unknown cost: " + normalized);
			}
		}

		return result;
	}
}
