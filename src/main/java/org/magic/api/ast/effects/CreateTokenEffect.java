package org.magic.api.ast.effects;

public record CreateTokenEffect(
        String tokenDescription,
        int quantity
) implements EffectNode {
}