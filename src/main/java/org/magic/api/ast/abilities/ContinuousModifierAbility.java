package org.magic.api.ast.abilities;

import org.magic.api.ast.interfaces.AbilityNode;
import org.magic.api.ast.interfaces.ModifierNode;
import org.magic.api.ast.interfaces.SelectorNode;
import org.magic.api.ast.interfaces.visitors.AbilityVisitor;

public record ContinuousModifierAbility(String text,SelectorNode affected, ModifierNode modifier)
		implements AbilityNode {

	@Override
	public <T> T accept(AbilityVisitor<T> visitor) {
		return visitor.visit(this);
	}
}