package org.magic.api.ast.abilities;

import java.util.List;

import org.magic.api.ast.interfaces.AbilityNode;
import org.magic.api.ast.interfaces.AbilityVisitor;
import org.magic.api.ast.interfaces.EffectNode;

public record SagaAbility(int num, List<EffectNode> effects) implements AbilityNode {

	@Override
	public <T> T accept(AbilityVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
