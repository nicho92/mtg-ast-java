package org.magic.api.ast.effects;

public sealed interface EffectNode permits DrawCardsEffect,
        GainLifeEffect,
        AddManaEffect,
        DealDamageEffect,
        DestroyTargetEffect,
        CreateTokenEffect,
        PutCountersEffect,
        ExileEffect,
        ReturnEffect,
        DiscardCardsEffect,
        SacrificeEffect,
        UnknownEffect 
        
        { }