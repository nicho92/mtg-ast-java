package org.magic.api.ast.util;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.magic.api.ast.engine.CardAst;
import org.magic.api.ast.abilities.*;
import org.magic.api.ast.costs.*;
import org.magic.api.ast.effects.*;
import org.magic.api.ast.triggers.*;

/**
 * Façade simplifiée pour interroger les informations d'une carte MTG.
 * Fournit une API intuitive pour accéder aux abilities, costs et effects
 * sans avoir à gérer les conversions de type et les patterns match.
 */
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
            .filter(a -> a instanceof KeywordAbility)
            .map(a -> (KeywordAbility) a)
            .collect(Collectors.toList());
    }
    
    public List<KeywordGroupAbility> getKeywordGroups() {
        return card.getAbilities().stream()
            .filter(a -> a instanceof KeywordGroupAbility)
            .map(a -> (KeywordGroupAbility) a)
            .collect(Collectors.toList());
    }
    
    public List<ActivatedAbility> getActivatedAbilities() {
        return card.getAbilities().stream()
            .filter(a -> a instanceof ActivatedAbility)
            .map(a -> (ActivatedAbility) a)
            .collect(Collectors.toList());
    }
    
    public List<TriggeredAbility> getTriggeredAbilities() {
        return card.getAbilities().stream()
            .filter(a -> a instanceof TriggeredAbility)
            .map(a -> (TriggeredAbility) a)
            .collect(Collectors.toList());
    }
    
    public List<StaticAbility> getStaticAbilities() {
        return card.getAbilities().stream()
            .filter(a -> a instanceof StaticAbility)
            .map(a -> (StaticAbility) a)
            .collect(Collectors.toList());
    }
    
    public List<ModalAbility> getModalAbilities() {
        return card.getAbilities().stream()
            .filter(a -> a instanceof ModalAbility)
            .map(a -> (ModalAbility) a)
            .collect(Collectors.toList());
    }
    
    // ============ COST EXTRACTION ============
    
    public List<CostNode> getAllCosts() {
        return getActivatedAbilities().stream()
            .flatMap(a -> a.costs().stream())
            .collect(Collectors.toList());
    }
    
    public List<ManaCost> getManaCosts() {
        return getAllCosts().stream()
            .filter(c -> c instanceof ManaCost)
            .map(c -> (ManaCost) c)
            .collect(Collectors.toList());
    }
    
    public List<TapCost> getTapCosts() {
        return getAllCosts().stream()
            .filter(c -> c instanceof TapCost)
            .map(c -> (TapCost) c)
            .collect(Collectors.toList());
    }
    
    public List<SacrificeCost> getSacrificeCosts() {
        return getAllCosts().stream()
            .filter(c -> c instanceof SacrificeCost)
            .map(c -> (SacrificeCost) c)
            .collect(Collectors.toList());
    }
    
    // ============ EFFECT EXTRACTION ============
    
    public List<EffectNode> getAllEffects() {
        List<EffectNode> effects = new ArrayList<>();
        
        getActivatedAbilities().forEach(a -> effects.addAll(a.effects()));
        getTriggeredAbilities().forEach(a -> effects.addAll(a.effects()));
        
        return effects;
    }
    
    public List<DrawCardsEffect> getDrawEffects() {
        return getAllEffects().stream()
            .filter(e -> e instanceof DrawCardsEffect)
            .map(e -> (DrawCardsEffect) e)
            .collect(Collectors.toList());
    }
    
    public List<AddManaEffect> getAddManaEffects() {
        return getAllEffects().stream()
            .filter(e -> e instanceof AddManaEffect)
            .map(e -> (AddManaEffect) e)
            .collect(Collectors.toList());
    }
    
    public List<DealDamageEffect> getDealDamageEffects() {
        return getAllEffects().stream()
            .filter(e -> e instanceof DealDamageEffect)
            .map(e -> (DealDamageEffect) e)
            .collect(Collectors.toList());
    }
    
    public List<CreateTokenEffect> getCreateTokenEffects() {
        return getAllEffects().stream()
            .filter(e -> e instanceof CreateTokenEffect)
            .map(e -> (CreateTokenEffect) e)
            .collect(Collectors.toList());
    }
    
    // ============ TRIGGER EXTRACTION ============
    
    public List<TriggerNode> getAllTriggers() {
        return getTriggeredAbilities().stream()
            .map(TriggeredAbility::trigger)
            .collect(Collectors.toList());
    }
    
    public List<DiesTrigger> getDiesTriggers() {
        return getAllTriggers().stream()
            .filter(t -> t instanceof DiesTrigger)
            .map(t -> (DiesTrigger) t)
            .collect(Collectors.toList());
    }
    
    public List<EntersBattlefieldTrigger> getEtbTriggers() {
        return getAllTriggers().stream()
            .filter(t -> t instanceof EntersBattlefieldTrigger)
            .map(t -> (EntersBattlefieldTrigger) t)
            .collect(Collectors.toList());
    }
    
    // ============ CONVENIENCE QUERIES ============
    
    /**
     * Vérifie si la carte a un keyword spécifique
     */
    public boolean hasKeyword(String keyword) {
        return getKeywordAbilities().stream()
            .anyMatch(k -> k.keyword().equalsIgnoreCase(keyword)) || getKeywordGroups().stream().anyMatch(kga->kga.keywords().stream().anyMatch(ka->ka.keyword().equalsIgnoreCase(keyword)));
    }
    
    /**
     * Récupère tous les triggers de type Deaths
     */
    public boolean hasDiesTrigger() {
        return !getDiesTriggers().isEmpty();
    }
    
    /**
     * Compte le nombre d'abilities totales
     */
    public int getAbilityCount() {
        return card.getAbilities().size();
    }
    
    /**
     * Vérifie si la carte a des abilities activées
     */
    public boolean hasActivatedAbilities() {
        return !getActivatedAbilities().isEmpty();
    }
}