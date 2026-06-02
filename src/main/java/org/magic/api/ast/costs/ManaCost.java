package org.magic.api.ast.costs;

public record ManaCost(
        String manaCost
) implements CostNode {
}