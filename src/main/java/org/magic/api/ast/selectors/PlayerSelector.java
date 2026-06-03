package org.magic.api.ast.selectors;

public record PlayerSelector(
        boolean target
) implements TargetSelectorNode {
}