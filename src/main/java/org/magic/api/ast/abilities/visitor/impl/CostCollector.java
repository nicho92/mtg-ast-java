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
import org.magic.api.ast.costs.CostNode;
import org.magic.api.ast.interfaces.AbilityVisitor;

/**
 * Concrete visitor implementation that collects all costs from abilities.
 * Only activated abilities have costs.
 * 
 * Usage:
 * <pre>
 * CostCollector collector = new CostCollector();
 * var allCosts = card.getAbilities().stream()
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
        //return ability.modes().stream().flatMap(mode -> mode..costs().stream()).collect(Collectors.toList());
    	return new ArrayList<>();
    }
    
    @Override
    public List<CostNode> visit(ReplacementEffectAbility ability) {
        return new ArrayList<>();
    }
    
    @Override
    public List<CostNode> visit(ContinuousModifierAbility ability) {
        return new ArrayList<>();
    }

	@Override
	public List<CostNode> visit(WordAbility wordAbility) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CostNode> visit(SagaAbility sagaAbility) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CostNode> visit(PlaneswalkerAbility planeswalkerAbility) {
		// TODO Auto-generated method stub
		return null;
	}
}