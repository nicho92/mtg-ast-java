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
import org.magic.api.ast.costs.CostNode;
import org.magic.api.ast.effects.EffectNode;
import org.magic.api.ast.engine.CardAst;
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

	public List<KeywordAbility> getKeywordsAbilities() {
		var result = new ArrayList<KeywordAbility>();

		card.getAbilities().stream().filter(KeywordGroupAbility.class::isInstance).map(a -> (KeywordGroupAbility) a)
				.forEach(kga -> {
					result.addAll(kga.keywords());
				});

		return result;
	}

	public List<ActivatedAbility> getActivatedAbilities() {
		return card.getAbilities().stream().filter(ActivatedAbility.class::isInstance).map(a -> (ActivatedAbility) a)
				.toList();
	}

	public List<TriggeredAbility> getTriggeredAbilities() {
		return card.getAbilities().stream().filter(TriggeredAbility.class::isInstance).map(a -> (TriggeredAbility) a)
				.toList();
	}

	public List<StaticAbility> getStaticAbilities() {
		return card.getAbilities().stream().filter(StaticAbility.class::isInstance).map(a -> (StaticAbility) a)
				.toList();
	}

	public List<ModalAbility> getModalAbilities() {
		return card.getAbilities().stream().filter(ModalAbility.class::isInstance).map(a -> (ModalAbility) a).toList();
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
		return getTriggeredAbilities().stream().map(ta->(Trigger)ta.trigger()).toList();
	}

	public List<Trigger> getTriggers(TriggerType t) {
		return getAllTriggers().stream().filter(trig->trig.type()==t).toList();
	}

	// ============ CONVENIENCE QUERIES ============

	/**
	 * Vérifie si la carte a un keyword spécifique
	 */
	public boolean hasKeyword(String keyword) {
		return getKeywordsAbilities().stream().anyMatch(ka -> ka.keyword().equalsIgnoreCase(keyword));
	}

	/**
	 * Vérifie si la carte a des abilities activées
	 */
	public boolean hasActivatedAbilities() {
		return !getActivatedAbilities().isEmpty();
	}
}