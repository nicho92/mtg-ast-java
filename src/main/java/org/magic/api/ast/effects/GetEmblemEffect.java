package org.magic.api.ast.effects;

import org.magic.api.ast.interfaces.EffectNode;

public record GetEmblemEffect(String text, String emblemDescription) implements EffectNode {
}