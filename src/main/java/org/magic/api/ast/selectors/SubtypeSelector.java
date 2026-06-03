package org.magic.api.ast.selectors;

public record SubtypeSelector(
        String subtype
) implements TargetSelectorNode {
}
