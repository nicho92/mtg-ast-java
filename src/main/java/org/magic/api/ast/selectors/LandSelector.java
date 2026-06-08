package org.magic.api.ast.selectors;

public record LandSelector(boolean target, boolean another, boolean controlledByYou) implements TargetSelectorNode {
}