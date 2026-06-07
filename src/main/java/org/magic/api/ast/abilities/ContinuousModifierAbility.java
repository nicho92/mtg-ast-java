package org.magic.api.ast.abilities;

import org.magic.api.ast.abilities.visitor.AbilityVisitor;
import org.magic.api.ast.modifiers.ModifierNode;
import org.magic.api.ast.selectors.TargetSelectorNode;

public record ContinuousModifierAbility(
        TargetSelectorNode affectedObjects,
        ModifierNode modifier
) implements AbilityNode {
	
	@Override
	public <T> T accept(AbilityVisitor<T> visitor) {
		return visitor.visit(this);
	}
}