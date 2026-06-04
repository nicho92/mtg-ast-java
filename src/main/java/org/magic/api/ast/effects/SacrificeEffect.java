package org.magic.api.ast.effects;

import org.magic.api.ast.selectors.TargetSelectorNode;

public record SacrificeEffect(
        TargetSelectorNode player,
        TargetSelectorNode permanent
) implements EffectNode {
}
