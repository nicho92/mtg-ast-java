package org.magic.api.ast.abilities;

import org.magic.api.ast.abilities.visitor.AbilityVisitor;

public record KeywordAbility(String keyword, String parameter) implements AbilityNode {
	
	@Override
	public <T> T accept(AbilityVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
