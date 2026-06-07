package org.magic.api.ast.abilities;

import org.magic.api.ast.abilities.visitor.AbilityVisitor;

public record StaticAbility(String oracleText) implements AbilityNode {
	
	@Override
	public <T> T accept(AbilityVisitor<T> visitor) {
		return visitor.visit(this);
	}
}