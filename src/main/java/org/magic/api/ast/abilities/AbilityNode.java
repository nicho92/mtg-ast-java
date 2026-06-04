package org.magic.api.ast.abilities;

public sealed interface AbilityNode permits KeywordAbility, KeywordGroupAbility, TriggeredAbility, ActivatedAbility, StaticAbility, SagaAbility, ModalAbility, ReplacementEffectAbility, ContinuousModifierAbility {

}