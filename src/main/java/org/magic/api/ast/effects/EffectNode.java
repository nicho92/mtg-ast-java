package org.magic.api.ast.effects;

public sealed interface EffectNode
permits DrawCardsEffect,
        GainLifeEffect,
        AddManaEffect,
        UnknownEffect {
}