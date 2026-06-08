package org.magic.api.ast.abilities;

import org.magic.api.ast.interfaces.AbilityNode;
import org.magic.api.ast.interfaces.AbilityVisitor;

public record WordAbility(String abilityWord, AbilityNode ability) implements AbilityNode {
	@Override
	public <T> T accept(AbilityVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
