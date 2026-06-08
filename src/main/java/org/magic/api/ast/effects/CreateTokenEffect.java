package org.magic.api.ast.effects;

import org.magic.api.ast.interfaces.EffectNode;

public record CreateTokenEffect(String tokenDescription, int quantity) implements EffectNode {
}