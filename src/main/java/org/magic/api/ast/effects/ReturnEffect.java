package org.magic.api.ast.effects;

import org.magic.api.ast.interfaces.EffectNode;
import org.magic.api.ast.interfaces.SelectorNode;

public record ReturnEffect(String text, SelectorNode target, String destination) implements EffectNode {
}
