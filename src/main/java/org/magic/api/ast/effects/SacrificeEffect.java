package org.magic.api.ast.effects;

import org.magic.api.ast.interfaces.EffectNode;
import org.magic.api.ast.interfaces.SelectorNode;

public record SacrificeEffect(String text, SelectorNode player, SelectorNode permanent) implements EffectNode {
}
