package org.magic.api.ast.costs.parsers;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.magic.api.ast.costs.ManaValue;
import org.magic.api.ast.costs.model.ManaSymbol;
import org.magic.api.ast.interfaces.CostNode;
import org.magic.api.ast.interfaces.parsers.AbstractParser;
import org.magic.api.ast.interfaces.parsers.CostParser;

public class ManaCostParser extends AbstractParser<List<CostNode>> implements CostParser {

	@Override
	protected String getPattern() {
		return "\\{([^}]+)}";
	}
	
	@Override
	public boolean supports(String text) {
		 return Pattern.compile(getPattern(),Pattern.CASE_INSENSITIVE).matcher(text).find();
	}
	
	@Override
	public List<CostNode> parse(String text) {
		var matcher = match(text);
		var ret = new ArrayList<ManaSymbol>();
		
		while (matcher.find()) {
			ret.add(ManaSymbol.parseByCode(matcher.group(1)));
		}
		return List.of(new ManaValue(text,ret));
	}
	
}