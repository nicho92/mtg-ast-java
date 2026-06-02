package org.magic.api.ast.effects;

public record DealDamageEffect(int amount,String target) implements EffectNode {}