package org.magic.api.ast.abilities.visitor.impl;

import org.magic.api.ast.abilities.ActivatedAbility;
import org.magic.api.ast.abilities.ContinuousModifierAbility;
import org.magic.api.ast.abilities.KeywordsAbility;
import org.magic.api.ast.abilities.ModalAbility;
import org.magic.api.ast.abilities.PlaneswalkerAbility;
import org.magic.api.ast.abilities.ReplacementEffectAbility;
import org.magic.api.ast.abilities.SagaAbility;
import org.magic.api.ast.abilities.StaticAbility;
import org.magic.api.ast.abilities.TriggeredAbility;
import org.magic.api.ast.abilities.WordAbility;
import org.magic.api.ast.abilities.model.Keyword;
import org.magic.api.ast.interfaces.visitors.AbstractVisitor;

/**
 * Concrete visitor implementation that generates human-readable descriptions of
 * abilities. Useful for debugging, logging, or displaying ability information
 * to users.
 * 
 * Usage:
 * 
 * <pre>
 * var describer = new AbilityDescriber();
 * for (AbilityNode ability : card.getAbilities()) {
 * 	String description = ability.accept(describer);
 * 	System.out.println(description);
 * }
 * </pre>
 */
public class AbilityDescriber extends AbstractVisitor<String> {

	@Override
	public String visit(KeywordsAbility ability) {
		String keywords = ability.keywords().stream().map(Keyword::name).reduce((a, b) -> a + ", " + b)
				.orElse("(empty)");
		return String.format("Keyword Group: %s", keywords);
	}

	@Override
	public String visit(ActivatedAbility ability) {
		int costCount = ability.costs().size();
		int effectCount = ability.effects().size();
		return String.format("Activated Ability: %d cost(s) → %d effect(s)", costCount, effectCount);
	}

	@Override
	public String visit(TriggeredAbility ability) {
		int effectCount = ability.effects().size();
		String triggerType = ability.trigger().getClass().getSimpleName();
		return String.format("Triggered Ability: %s → %d effect(s)", triggerType, effectCount);
	}

	@Override
	public String visit(StaticAbility ability) {
		return String.format("Static Ability: %s", ability.text());
	}

	@Override
	public String visit(ModalAbility ability) {
		int modeCount = ability.modes().size();
		return String.format("Modal Ability: Choose %d mode(s)", modeCount);
	}

	@Override
	public String visit(ReplacementEffectAbility ability) {
		int effectCount = ability.replacementEffects().size();
		return String.format("Replacement Effect Ability: %s → %d effect(s)", ability.replacedEvent(), effectCount);
	}

	@Override
	public String visit(ContinuousModifierAbility ability) {
		String modifierType = ability.modifier().getClass().getSimpleName();
		return String.format("Continuous Modifier: Applies %s", modifierType);
	}

	@Override
	public String visit(PlaneswalkerAbility ability) {
		return String.format("Planeswalker [%s] : %s", ability.loyalty(), ability.effects());
	}

	@Override
	public String visit(SagaAbility ability) {
		return String.format("Saga [%s] : %s", ability.num(), ability.effects());
	}

	@Override
	public String visit(WordAbility ability) {
		return String.format("Word: %s", ability.abilityWord());
	}
}