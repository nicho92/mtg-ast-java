package org.magic.api.ast.costs;

import java.util.List;

public record ManaCost(List<String> manaCost) implements CostNode {
}