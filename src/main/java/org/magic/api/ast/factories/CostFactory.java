package org.magic.api.ast.factories;

import java.util.ArrayList;
import java.util.List;

import org.magic.api.ast.costs.CostNode;
import org.magic.api.ast.costs.parsers.ManaCostParser;
import org.magic.api.ast.costs.parsers.SacrificeCostParser;
import org.magic.api.ast.costs.parsers.TapCostParser;
import org.magic.api.ast.interfaces.CostParser;

public class CostFactory {

    private final List<CostParser> parsers;
    public static final CostFactory INSTANCE = new CostFactory();
    
    
    private CostFactory() {

        this.parsers = List.of(
                new TapCostParser(),
                new ManaCostParser(),
                new SacrificeCostParser()
        );
    }

    public List<CostNode> parse(String costText) {

        List<CostNode> result = new ArrayList<>();
        String[] parts = costText.split(",");

        for (String part : parts) {

            String normalized = part.trim();

            boolean matched = false;

            for (CostParser parser : parsers) {

                if (parser.supports(normalized)) {

                    result.addAll(parser.parse(normalized));
                    matched = true;
                    break;
                }
            }

            if (!matched) {
                throw new IllegalArgumentException(
                        "Unknown cost: " + normalized
                );
            }
        }

        return result;
    }
}
