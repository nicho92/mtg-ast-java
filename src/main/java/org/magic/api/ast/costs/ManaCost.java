package org.magic.api.ast.costs;

import java.util.List;

import org.magic.api.ast.interfaces.CostNode;

public record ManaCost(List<String> manaCost) implements CostNode {
}