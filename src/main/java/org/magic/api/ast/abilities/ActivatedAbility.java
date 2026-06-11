package org.magic.api.ast.abilities;

import java.util.List;

import org.magic.api.ast.interfaces.AbilityNode;
import org.magic.api.ast.interfaces.CostNode;
import org.magic.api.ast.interfaces.EffectNode;
import org.magic.api.ast.interfaces.visitors.AbilityVisitor;

public record ActivatedAbility(String text,List<CostNode> costs, List<EffectNode> effects) implements AbilityNode {

	@Override
	public <T> T accept(AbilityVisitor<T> visitor) {
		return visitor.visit(this);
	}
}