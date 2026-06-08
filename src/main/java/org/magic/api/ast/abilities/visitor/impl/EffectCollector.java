package org.magic.api.ast.abilities.visitor.impl;

import java.util.ArrayList;
import java.util.List;

import org.magic.api.ast.abilities.ActivatedAbility;
import org.magic.api.ast.abilities.ModalAbility;
import org.magic.api.ast.abilities.PlaneswalkerAbility;
import org.magic.api.ast.abilities.ReplacementEffectAbility;
import org.magic.api.ast.abilities.SagaAbility;
import org.magic.api.ast.abilities.TriggeredAbility;
import org.magic.api.ast.interfaces.EffectNode;
import org.magic.api.ast.interfaces.visitors.AbstractVisitor;

/**
 * Concrete visitor implementation that collects all effects from abilities.
 * 
 * Usage:
 * 
 * <pre>
 * EffectCollector collector = new EffectCollector();
 * var allEffects = card.getAbilities().stream().map(ability -> ability.accept(collector)).flatMap(List::stream)
 * 		.collect(Collectors.toList());
 * </pre>
 */
public class EffectCollector extends AbstractVisitor<List<EffectNode>> {

	@Override
	public List<EffectNode> visit(ActivatedAbility ability) {
		// Return all effects from the activated ability
		return new ArrayList<>(ability.effects());
	}

	@Override
	public List<EffectNode> visit(TriggeredAbility ability) {
		// Return all effects from the triggered ability
		return new ArrayList<>(ability.effects());
	}

	@Override
	public List<EffectNode> visit(ModalAbility ability) {
		// Collect effects from all modes
		return ability.modes().stream().flatMap(mode -> mode.effects().stream()).toList();
	}

	@Override
	public List<EffectNode> visit(ReplacementEffectAbility ability) {
		// Return all replacement effects
		return new ArrayList<>(ability.replacementEffects());
	}

	@Override
	public List<EffectNode> visit(SagaAbility ability) {
		return new ArrayList<>(ability.effects());
	}

	@Override
	public List<EffectNode> visit(PlaneswalkerAbility ability) {
		return new ArrayList<>(ability.effects());
	}
}
