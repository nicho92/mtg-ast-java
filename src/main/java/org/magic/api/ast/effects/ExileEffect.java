package org.magic.api.ast.effects;

import org.magic.api.ast.interfaces.EffectNode;
import org.magic.api.ast.interfaces.SelectorNode;

public record ExileEffect(String text, SelectorNode target) implements EffectNode {
}
