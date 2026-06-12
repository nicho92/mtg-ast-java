package org.magic.api.ast.effects;

import org.magic.api.ast.interfaces.EffectNode;

public record CreateTokenEffect(String text, String tokenDescription, int quantity) implements EffectNode {
}