package org.magic.api.ast.selectors;

public record ArtifactSelector(
        boolean target
) implements TargetSelectorNode {
}