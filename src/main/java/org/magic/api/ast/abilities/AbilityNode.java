package org.magic.api.ast.abilities;

public sealed interface AbilityNode permits KeywordAbility, TriggeredAbility, ActivatedAbility, StaticAbility {}