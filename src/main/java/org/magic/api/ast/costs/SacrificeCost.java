package org.magic.api.ast.costs;

import org.magic.api.ast.interfaces.CostNode;
import org.magic.api.ast.selectors.TargetSelectorNode;

public record SacrificeCost(TargetSelectorNode target, int qty) implements CostNode {
}