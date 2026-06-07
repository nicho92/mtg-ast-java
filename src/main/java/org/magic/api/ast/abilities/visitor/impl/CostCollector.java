package org.magic.api.ast.abilities.visitor.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.magic.api.ast.abilities.*;
import org.magic.api.ast.abilities.visitor.AbilityVisitor;
import org.magic.api.ast.costs.CostNode;

/**
 * Concrete visitor implementation that collects all costs from abilities.
 * Only activated abilities have costs.
 * 
 * Usage:
 * <pre>
 * CostCollector collector = new CostCollector();
 * List<CostNode> allCosts = card.getAbilities().stream()
 *     .map(ability -> ability.accept(collector))
 *     .flatMap(List::stream)
 *     .collect(Collectors.toList());
 * </pre>
 */
public class CostCollector implements AbilityVisitor<List<CostNode>> {
    
    @Override
    public List<CostNode> visit(KeywordAbility ability) {
        return new ArrayList<>();
    }
    
    @Override
    public List<CostNode> visit(KeywordGroupAbility ability) {
        return new ArrayList<>();
    }
    
    @Override
    public List<CostNode> visit(ActivatedAbility ability) {
        // Return all costs from the activated ability
        return new ArrayList<>(ability.costs());
    }
    
    @Override
    public List<CostNode> visit(TriggeredAbility ability) {
        // Triggered abilities don't have costs
        return new ArrayList<>();
    }
    
    @Override
    public List<CostNode> visit(StaticAbility ability) {
        return new ArrayList<>();
    }
    
    @Override
    public List<CostNode> visit(ModalAbility ability) {
        // Modal abilities can have costs in their modes
        return ability.modes().stream()
            .flatMap(mode -> mode.costs().stream())
            .collect(Collectors.toList());
    }
    
    @Override
    public List<CostNode> visit(ReplacementEffectAbility ability) {
        return new ArrayList<>();
    }
    
    @Override
    public List<CostNode> visit(ContinuousModifierAbility ability) {
        return new ArrayList<>();
    }
}