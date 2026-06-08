package org.magic.api.ast.interfaces.parsers;

import java.util.List;

import org.magic.api.ast.interfaces.CostNode;

public interface CostParser {

	boolean supports(String text);

	List<CostNode> parse(String text);
}