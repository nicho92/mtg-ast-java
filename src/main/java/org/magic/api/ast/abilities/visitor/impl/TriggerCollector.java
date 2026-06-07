package org.magic.api.ast.abilities.visitor.impl;

import java.util.ArrayList;
import java.util.List;

import org.magic.api.ast.abilities.ActivatedAbility;
import org.magic.api.ast.abilities.ContinuousModifierAbility;
import org.magic.api.ast.abilities.KeywordAbility;
import org.magic.api.ast.abilities.KeywordGroupAbility;
import org.magic.api.ast.abilities.ModalAbility;
import org.magic.api.ast.abilities.PlaneswalkerAbility;
import org.magic.api.ast.abilities.ReplacementEffectAbility;
import org.magic.api.ast.abilities.SagaAbility;
import org.magic.api.ast.abilities.StaticAbility;
import org.magic.api.ast.abilities.TriggeredAbility;
import org.magic.api.ast.abilities.WordAbility;
import org.magic.api.ast.interfaces.AbilityVisitor;
import org.magic.api.ast.triggers.TriggerNode;

/**
 * Concrete visitor implementation that collects all triggers from abilities.
 * Only triggered abilities have triggers.
 * 
 * Usage:
 * <pre>
 * TriggerCollector collector = new TriggerCollector();
 * List<TriggerNode> allTriggers = card.getAbilities().stream()
 *     .map(ability -> ability.accept(collector))
 *     .flatMap(List::stream)
 *     .collect(Collectors.toList());
 * </pre>
 */
public class TriggerCollector implements AbilityVisitor<List<TriggerNode>> {
    
    @Override
    public List<TriggerNode> visit(KeywordAbility ability) {
        return new ArrayList<>();
    }
    
    @Override
    public List<TriggerNode> visit(KeywordGroupAbility ability) {
        return new ArrayList<>();
    }
    
    @Override
    public List<TriggerNode> visit(ActivatedAbility ability) {
        // Activated abilities don't have triggers
        return new ArrayList<>();
    }
    
    @Override
    public List<TriggerNode> visit(TriggeredAbility ability) {
        // Return the trigger from triggered ability
        return List.of(ability.trigger());
    }
    
    @Override
    public List<TriggerNode> visit(StaticAbility ability) {
        return new ArrayList<>();
    }
    
    @Override
    public List<TriggerNode> visit(ModalAbility ability) {
        // Modal abilities can have triggers in their modes
        //return ability.modes().stream().flatMap(mode -> mode.triggers().stream()).collect(Collectors.toList());
    	return new ArrayList<>();
    }
    
    @Override
    public List<TriggerNode> visit(ReplacementEffectAbility ability) {
        // Replacement effects are not the same as triggers
        return new ArrayList<>();
    }
    
    @Override
    public List<TriggerNode> visit(ContinuousModifierAbility ability) {
        return new ArrayList<>();
    }

	@Override
	public List<TriggerNode> visit(WordAbility wordAbility) {
		 return new ArrayList<>();
	}

	@Override
	public List<TriggerNode> visit(SagaAbility sagaAbility) {
		 return new ArrayList<>();
	}

	@Override
	public List<TriggerNode> visit(PlaneswalkerAbility planeswalkerAbility) {
		 return new ArrayList<>();
	}
}