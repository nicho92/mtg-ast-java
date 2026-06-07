package org.magic.api.ast.abilities.visitor.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.magic.api.ast.abilities.*;
import org.magic.api.ast.abilities.visitor.AbilityVisitor;
import org.magic.api.ast.costs.CostNode;
import org.magic.api.ast.effects.EffectNode;

/**
 * Concrete visitor implementation that collects all effects from abilities.
 * 
 * Usage:
 * <pre>
 * EffectCollector collector = new EffectCollector();
 * List<EffectNode> allEffects = card.getAbilities().stream()
 *     .map(ability -> ability.accept(collector))
 *     .flatMap(List::stream)
 *     .collect(Collectors.toList());
 * </pre>
 */
public class EffectCollector implements AbilityVisitor<List<EffectNode>> {
    
    @Override
    public List<EffectNode> visit(KeywordAbility ability) {
        // Keyword abilities typically don't have explicit effects
        return new ArrayList<>();
    }
    
    @Override
    public List<EffectNode> visit(KeywordGroupAbility ability) {
        // Keyword groups don't have explicit effects
        return new ArrayList<>();
    }
    
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
    public List<EffectNode> visit(StaticAbility ability) {
        // Static abilities don't have structured effects
        return new ArrayList<>();
    }
    
    @Override
    public List<EffectNode> visit(ModalAbility ability) {
        // Collect effects from all modes
        return ability.modes().stream()
            .flatMap(mode -> mode.effects().stream())
            .collect(Collectors.toList());
    }
    
    @Override
    public List<EffectNode> visit(ReplacementEffectAbility ability) {
        // Return all replacement effects
        return new ArrayList<>(ability.replacementEffects());
    }
    
    @Override
    public List<EffectNode> visit(ContinuousModifierAbility ability) {
        // Continuous modifiers don't have effects
        return new ArrayList<>();
    }
}
