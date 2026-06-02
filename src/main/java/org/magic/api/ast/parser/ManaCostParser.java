package org.magic.api.ast.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.magic.api.ast.costs.CostNode;
import org.magic.api.ast.costs.ManaCost;
import org.magic.api.ast.parser.interfaces.CostParser;

public class ManaCostParser implements CostParser {

    private static final Pattern MANA_SYMBOL =
            Pattern.compile("\\{([^}]+)}");

    @Override
    public boolean supports(String text) {
        return text.contains("{") && text.contains("}");
    }

    @Override
    public List<CostNode> parse(String text) {

        Matcher matcher = MANA_SYMBOL.matcher(text);

        List<String> symbols = new ArrayList<>();

        while (matcher.find()) {
            symbols.add(matcher.group(1));
        }

        if (symbols.isEmpty()) {
            return List.of();
        }

        return List.of(new ManaCost(String.join(",", symbols)));
    }
}