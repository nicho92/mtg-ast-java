package org.magic.api.ast.abilities;

import java.util.List;

import org.magic.api.ast.effects.EffectNode;
import org.magic.api.ast.triggers.TriggerNode;

public record TriggeredAbility( TriggerNode trigger,    List<EffectNode> effects) implements AbilityNode {
}