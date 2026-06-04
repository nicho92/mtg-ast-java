package org.magic.api.ast.abilities;

import java.util.List;

import org.magic.api.ast.effects.EffectNode;

public record ReplacementEffectAbility(
        String replacedEvent,
        List<EffectNode> replacementEffects
) implements AbilityNode {
}
