package org.magic.api.ast.costs.parsers;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.magic.api.ast.costs.CostNode;
import org.magic.api.ast.costs.SacrificeCost;
import org.magic.api.ast.parser.interfaces.CostParser;

public class SacrificeCostParser implements CostParser{

	private static final Pattern PATTERN = Pattern.compile("^Sacrifice\\s+(.+)$",Pattern.CASE_INSENSITIVE);
	
	
	@Override
	public boolean supports(String text) {
		 return PATTERN.matcher(text).matches();
	}

	@Override
	public List<CostNode> parse(String text) {

	    Matcher matcher =
	            PATTERN.matcher(text);

	    if (!matcher.matches()) {
	        return List.of();
	    }

	    String targetText =
	            matcher.group(1).trim();

	    return List.of(
	            new SacrificeCost(
	            		targetText
	            )
	    );
	}

}
