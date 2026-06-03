package org.magic.api.ast.costs.parsers;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.magic.api.ast.costs.CostNode;
import org.magic.api.ast.costs.SacrificeCost;
import org.magic.api.ast.factories.TargetSelectorFactory;
import org.magic.api.ast.interfaces.CostParser;

public class SacrificeCostParser implements CostParser{

	private static final Pattern PATTERN = Pattern.compile("^Sacrifice\\s+(a|an|another|one|two|three)\\s+(.+)$",Pattern.CASE_INSENSITIVE);
	
	
	@Override
	public boolean supports(String text) {
		 return PATTERN.matcher(text).matches();
	}

	@Override
	public List<CostNode> parse(String text) {

	    Matcher matcher =PATTERN.matcher(text);

	    if (!matcher.matches()) {
	        return List.of();
	    }

	    return List.of(
	            new SacrificeCost(
	            		TargetSelectorFactory.INSTANCE.parse(matcher.group(2).trim()),
	            		parseQuantity(matcher.group(1).trim())
	            )
	    );
	}
	
	private int parseQuantity(String quantity) {

	    return switch (quantity.toLowerCase()) {

	        case "a", "an", "one","another" -> 1;
	        case "two" -> 2;
	        case "three" -> 3;

	        default -> 1;
	    };
	}

}
