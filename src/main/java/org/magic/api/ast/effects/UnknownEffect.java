package org.magic.api.ast.effects;

import org.magic.api.ast.interfaces.EffectNode;

public record UnknownEffect(String text) implements EffectNode {
}