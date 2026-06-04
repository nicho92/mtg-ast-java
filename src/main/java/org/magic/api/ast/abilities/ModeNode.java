package org.magic.api.ast.abilities;

import java.util.List;

import org.magic.api.ast.effects.EffectNode;

public record ModeNode(
        String oracleText,
        List<EffectNode> effects
) {
}
