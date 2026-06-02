package org.magic.api.ast.parser;

import java.util.List;
import java.util.regex.Pattern;

import org.magic.api.ast.costs.CostNode;
import org.magic.api.ast.costs.TapCost;
import org.magic.api.ast.parser.interfaces.CostParser;

public class TapCostParser implements CostParser {

    private static final Pattern TAP =
            Pattern.compile("\\{T\\}");

    private static final Pattern UNTAP =
            Pattern.compile("\\{Q\\}");

    @Override
    public boolean supports(String text) {
        return TAP.matcher(text).find()
                || UNTAP.matcher(text).find();
    }

    @Override
    public List<CostNode> parse(String text) {

        if (TAP.matcher(text).find()) {
            return List.of(TapCost.INSTANCE);
        }

//        // extension future possible
//        if (UNTAP.matcher(text).find()) {
//            return List.of(new UntapCost());
//        }

        return List.of();
    }
}