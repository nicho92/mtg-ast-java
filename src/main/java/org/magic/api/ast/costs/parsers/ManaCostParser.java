package org.magic.api.ast.costs.parsers;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.magic.api.ast.costs.CostNode;
import org.magic.api.ast.costs.ManaCost;
import org.magic.api.ast.interfaces.CostParser;

public class ManaCostParser implements CostParser {

    private static final Pattern MANA_SYMBOL = Pattern.compile("\\{([^}]+)}");
	//private static final Pattern MANA_SYMBOL = Pattern.compile("([WUBRG])")
	
    @Override
    public boolean supports(String text) {
        return text.contains("{") && text.contains("}");
    }

    @Override
    public List<CostNode> parse(String text) {

        var matcher = MANA_SYMBOL.matcher(text);

       var symbols = new ArrayList<String>();

        while (matcher.find()) {
            symbols.add(matcher.group(1));
        }

        if (symbols.isEmpty()) {
            return List.of();
        }

        return List.of(new ManaCost(symbols));
    }
}