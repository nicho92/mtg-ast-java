package org.magic.api.ast.costs.parsers;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.magic.api.ast.costs.ManaValue;
import org.magic.api.ast.costs.model.ManaSymbol;
import org.magic.api.ast.interfaces.CostNode;
import org.magic.api.ast.interfaces.parsers.CostParser;

public class ManaCostParser implements CostParser {

	private static final Pattern MANA_SYMBOL = Pattern.compile("\\{([^}]+)}");

	@Override
	public boolean supports(String text) {
		return MANA_SYMBOL.matcher(text).find();
	}

	@Override
	public List<CostNode> parse(String text) {
		var matcher = MANA_SYMBOL.matcher(text);
		var ret = new ArrayList<ManaSymbol>();
		
		while (matcher.find()) {
			ret.add(ManaSymbol.parseByCode(matcher.group(1)));
		}
		return List.of(new ManaValue(ret));
	}
	
}