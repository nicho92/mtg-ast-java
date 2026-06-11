package org.magic.api.ast.util;

import java.util.ArrayList;
import java.util.List;

import org.magic.api.ast.abilities.ActivatedAbility;
import org.magic.api.ast.abilities.ContinuousModifierAbility;
import org.magic.api.ast.abilities.KeywordsAbility;
import org.magic.api.ast.abilities.ModalAbility;
import org.magic.api.ast.abilities.SagaAbility;
import org.magic.api.ast.abilities.StaticAbility;
import org.magic.api.ast.abilities.TriggeredAbility;
import org.magic.api.ast.abilities.WordAbility;
import org.magic.api.ast.abilities.model.Keyword;
import org.magic.api.ast.engine.CardAst;
import org.magic.api.ast.interfaces.AbilityNode;
import org.magic.api.ast.interfaces.CostNode;
import org.magic.api.ast.interfaces.EffectNode;
import org.magic.api.ast.triggers.Trigger;
import org.magic.api.ast.triggers.TriggerType;

public class ASTFacade {

	private final CardAst card;

	public ASTFacade(CardAst card) {
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

	public List<Keyword> getKeywordsAbilities() {
		var result = new ArrayList<Keyword>();

		getAbilities(KeywordsAbility.class).forEach(kga -> result.addAll(kga.keywords()));

		return result;
	}
	
	@SuppressWarnings("unchecked")
	public <T extends AbilityNode> List<T> getAbilities(Class<T> classe){
		return card.getAbilities().stream().filter(classe::isInstance).map(a -> (T) a).toList();
	}
	
	public List<ContinuousModifierAbility> getContinuousAbilities(){
		return getAbilities(ContinuousModifierAbility.class);
	}
	
	public List<ActivatedAbility> getActivatedAbilities() {
		return getAbilities(ActivatedAbility.class);
	}

	public List<TriggeredAbility> getTriggeredAbilities() {
		return getAbilities(TriggeredAbility.class);
	}

	public List<StaticAbility> getStaticAbilities() {
		return getAbilities(StaticAbility.class);
	}

	public List<ModalAbility> getModalAbilities() {
		return  getAbilities(ModalAbility.class);
	}
	
	public List<SagaAbility> getSagaAbilities() {
		return getAbilities(SagaAbility.class);
	}
	
	public List<WordAbility> getWordAbilities() {
		return getAbilities(WordAbility.class);
	}
	

	// ============ COST EXTRACTION ============

	public List<CostNode> getAllCosts() {
		return getActivatedAbilities().stream().flatMap(a -> a.costs().stream()).toList();
	}

	@SuppressWarnings("unchecked")
	public <T extends CostNode> List<T> getCosts(Class<T> classe) {
		return getAllCosts().stream().filter(classe::isInstance).map(c -> (T) c).toList();
	}

	// ============ EFFECT EXTRACTION ============

	public List<EffectNode> getAllEffects() {
		List<EffectNode> effects = new ArrayList<>();

		getActivatedAbilities().forEach(a -> effects.addAll(a.effects()));
		getTriggeredAbilities().forEach(a -> effects.addAll(a.effects()));

		return effects;
	}

	@SuppressWarnings("unchecked")
	public <T extends EffectNode> List<T> getEffects(Class<T> classe) {
		return getAllEffects().stream().filter(classe::isInstance).map(e -> (T) e).toList();
	}

	// ============ TRIGGER EXTRACTION ============

	public List<Trigger> getAllTriggers() {
		return getTriggeredAbilities().stream().map(ta->ta.trigger()).toList();
	}

	public List<Trigger> getTriggers(TriggerType t) {
		return getAllTriggers().stream().filter(trig->trig.type()==t).toList();
	}

	// ============ CONVENIENCE QUERIES ============

	/**
	 * Vérifie si la carte a un keyword spécifique
	 */
	public boolean hasKeyword(String keyword) {
		return getKeywordsAbilities().stream().anyMatch(ka -> ka.name().equalsIgnoreCase(keyword));
	}

	/**
	 * Vérifie si la carte a des abilities activées
	 */
	public boolean hasActivatedAbilities() {
		return !getActivatedAbilities().isEmpty();
	}
}