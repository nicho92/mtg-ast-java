package org.magic.api.ast.abilities;

import org.magic.api.ast.interfaces.AbilityNode;
import org.magic.api.ast.interfaces.SelectorNode;
import org.magic.api.ast.interfaces.visitors.AbilityVisitor;
import org.magic.api.ast.modifiers.ModifierNode;

public record ContinuousModifierAbility(SelectorNode affectedObjects, ModifierNode modifier)
		implements AbilityNode {

	@Override
	public <T> T accept(AbilityVisitor<T> visitor) {
		return visitor.visit(this);
	}
}