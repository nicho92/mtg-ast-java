package org.magic.api.ast.effects;

import org.magic.api.ast.interfaces.EffectNode;
import org.magic.api.ast.interfaces.SelectorNode;

public record DiscardCardsEffect(String text, SelectorNode player, int amount) implements EffectNode {
}
