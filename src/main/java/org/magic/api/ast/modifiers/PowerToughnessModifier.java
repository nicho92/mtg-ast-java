package org.magic.api.ast.modifiers;

import org.magic.api.ast.costs.model.DurationType;
import org.magic.api.ast.interfaces.ModifierNode;

public record PowerToughnessModifier(int power, int toughness,DurationType duration) implements ModifierNode {
}
