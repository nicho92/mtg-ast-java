package org.magic.api.ast.abilities;

import java.util.List;

import org.magic.api.ast.abilities.visitor.AbilityVisitor;

public record KeywordGroupAbility(List<KeywordAbility> keywords) implements AbilityNode {
	
	@Override
	public <T> T accept(AbilityVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
