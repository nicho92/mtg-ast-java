package org.magic.api.ast.selectors;

public record CreatureSelector(boolean target, boolean another, boolean controlledByYou) implements TargetSelectorNode {
}