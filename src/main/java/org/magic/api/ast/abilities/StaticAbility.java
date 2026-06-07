package org.magic.api.ast.abilities;

import org.magic.api.ast.interfaces.AbilityVisitor;

public record StaticAbility(String oracleText) implements AbilityNode {
	
	@Override
	public <T> T accept(AbilityVisitor<T> visitor) {
		return visitor.visit(this);
	}
}