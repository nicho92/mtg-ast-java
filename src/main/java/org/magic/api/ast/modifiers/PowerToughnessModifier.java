package org.magic.api.ast.modifiers;

import org.magic.api.ast.interfaces.ModifierNode;

public record PowerToughnessModifier(int powerDelta, int toughnessDelta) implements ModifierNode {
}
