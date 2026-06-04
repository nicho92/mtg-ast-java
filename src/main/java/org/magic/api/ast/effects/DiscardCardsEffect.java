package org.magic.api.ast.effects;

import org.magic.api.ast.selectors.TargetSelectorNode;

public record DiscardCardsEffect(
        TargetSelectorNode player,
        int amount
) implements EffectNode {
}
