package org.magic.api.ast.abilities;

import java.util.List;

import org.magic.api.ast.effects.EffectNode;
import org.magic.api.ast.interfaces.AbilityVisitor;

public record ReplacementEffectAbility(
        String replacedEvent,
        List<EffectNode> replacementEffects
) implements AbilityNode {
	
	@Override
	public <T> T accept(AbilityVisitor<T> visitor) {
		return visitor.visit(this);
	}
}