package org.magic.api.ast.modifiers;

public record PowerToughnessModifier(
        int powerDelta,
        int toughnessDelta
) implements ModifierNode {
}
