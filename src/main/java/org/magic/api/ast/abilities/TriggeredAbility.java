package org.magic.api.ast.abilities;

import java.util.List;

import org.magic.api.ast.effects.EffectNode;
import org.magic.api.ast.interfaces.AbilityVisitor;
import org.magic.api.ast.triggers.TriggerNode;

public record TriggeredAbility(TriggerNode trigger, List<EffectNode> effects) implements AbilityNode {

	@Override
	public <T> T accept(AbilityVisitor<T> visitor) {
		return visitor.visit(this);
	}
}