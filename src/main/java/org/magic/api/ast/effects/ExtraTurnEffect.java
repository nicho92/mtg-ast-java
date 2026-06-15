package org.magic.api.ast.effects;

import org.magic.api.ast.interfaces.EffectNode;
import org.magic.api.ast.interfaces.SelectorNode;

public record ExtraTurnEffect(String text, int amount,SelectorNode target) implements EffectNode {
}