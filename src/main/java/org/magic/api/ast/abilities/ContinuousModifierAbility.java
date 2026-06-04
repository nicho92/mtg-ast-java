package org.magic.api.ast.abilities;

import org.magic.api.ast.modifiers.ModifierNode;
import org.magic.api.ast.selectors.TargetSelectorNode;

public record ContinuousModifierAbility(
        TargetSelectorNode affectedObjects,
        ModifierNode modifier
) implements AbilityNode {
}
