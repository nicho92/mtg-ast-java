package org.magic.api.ast.visitor.impl;

import java.util.List;

import org.magic.api.ast.abilities.TriggeredAbility;
import org.magic.api.ast.interfaces.visitors.AbstractVisitor;
import org.magic.api.ast.triggers.Trigger;

/**
 * Concrete visitor implementation that collects all triggers from abilities.
 * Only triggered abilities have triggers.
 * 
 * Usage:
 * 
 * <pre>
 * TriggerCollector collector = new TriggerCollector();
 * var allTriggers = card.getAbilities().stream().map(ability -> ability.accept(collector)).flatMap(List::stream)
 * 		.collect(Collectors.toList());
 * </pre>
 */
public class TriggerCollector extends AbstractVisitor<List<Trigger>> {

	@Override
	public List<Trigger> visit(TriggeredAbility ability) {
		// Return the trigger from triggered ability
		return List.of(ability.trigger());
	}

}