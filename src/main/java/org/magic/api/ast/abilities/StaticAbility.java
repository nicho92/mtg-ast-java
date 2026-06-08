package org.magic.api.ast.abilities;

import org.magic.api.ast.interfaces.AbilityNode;
import org.magic.api.ast.interfaces.visitors.AbilityVisitor;

public record StaticAbility(String text) implements AbilityNode {

	@Override
	public <T> T accept(AbilityVisitor<T> visitor) {
		return visitor.visit(this);
	}
}