package org.magic.api.ast.abilities.visitor.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.magic.api.ast.abilities.*;
import org.magic.api.ast.abilities.visitor.AbilityVisitor;
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
        return ability.modes().stream()
            .flatMap(mode -> mode.triggers().stream())
            .collect(Collectors.toList());
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
}