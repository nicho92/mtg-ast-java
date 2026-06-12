package org.magic.api.ast.effects;

import org.magic.api.ast.interfaces.EffectNode;

public record DrawCardsEffect(String text, int amount) implements EffectNode {
}