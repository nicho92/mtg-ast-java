package org.magic.api.ast.costs;

import org.magic.api.ast.interfaces.CostNode;
import org.magic.api.ast.interfaces.SelectorNode;

public record SacrificeCost(String text,SelectorNode target, int qty) implements CostNode {
	
}