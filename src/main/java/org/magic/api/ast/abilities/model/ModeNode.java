package org.magic.api.ast.abilities.model;

import java.util.List;

import org.magic.api.ast.interfaces.EffectNode;

public record ModeNode(String oracleText, List<EffectNode> effects) {
}
