package org.magic.api.ast.parser.interfaces;

import java.util.List;

import org.magic.api.ast.costs.CostNode;

public interface CostParser {

    boolean supports(String text);

    List<CostNode> parse(String text);
}