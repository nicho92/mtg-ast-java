package org.magic.api.ast.util;

import java.util.ArrayList;
import java.util.List;

import org.magic.api.ast.abilities.AbilityNode;
import org.magic.api.ast.abilities.ActivatedAbility;
import org.magic.api.ast.abilities.KeywordAbility;
import org.magic.api.ast.abilities.KeywordGroupAbility;
import org.magic.api.ast.abilities.ModalAbility;
import org.magic.api.ast.abilities.StaticAbility;
import org.magic.api.ast.abilities.TriggeredAbility;
import org.magic.api.ast.engine.CardAst;


public class CardQueryFacade {
    
    private final CardAst card;
    
    public CardQueryFacade(CardAst card) {
        this.card = card;
    }
    
    // ============ CARD INFO ============
    
    public String getCardName() {
        return card.getName();
    }
    
    public List<AbilityNode> getAllAbilities() {
        return new ArrayList<>(card.getAbilities());
    }
    
    // ============ ABILITY FILTERING ============
    
    public List<KeywordAbility> getKeywordAbilities() {
        return card.getAbilities().stream()
            .filter(KeywordAbility.class::isInstance)
            .map(a -> (KeywordAbility) a)
            .toList();
    }
    
    public List<KeywordGroupAbility> getKeywordGroups() {
        return card.getAbilities().stream()
            .filter(KeywordGroupAbility.class::isInstance)
            .map(a -> (KeywordGroupAbility) a)
            .toList();
    }
    
    public List<ActivatedAbility> getActivatedAbilities() {
        return card.getAbilities().stream()
            .filter(ActivatedAbility.class::isInstance)
            .map(a -> (ActivatedAbility) a)
         .toList();
    }
    
    public List<TriggeredAbility> getTriggeredAbilities() {
        return card.getAbilities().stream()
            .filter(TriggeredAbility.class::isInstance)
            .map(a -> (TriggeredAbility) a)
         .toList();
    }
    
    public List<StaticAbility> getStaticAbilities() {
        return card.getAbilities().stream()
            .filter(StaticAbility.class::isInstance)
            .map(a -> (StaticAbility) a)
         .toList();
    }
    
    public List<ModalAbility> getModalAbilities() {
        return card.getAbilities().stream()
            .filter(ModalAbility.class::isInstance)
            .map(a -> (ModalAbility) a)
         .toList();
    }
    
}