package org.magic.api.ast.abilities;

import java.util.List;

import org.magic.api.ast.interfaces.AbilityNode;
import org.magic.api.ast.interfaces.EffectNode;
import org.magic.api.ast.interfaces.visitors.AbilityVisitor;

public record SagaAbility(String text,int num, List<EffectNode> effects) implements AbilityNode {

	@Override
	public <T> T accept(AbilityVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
