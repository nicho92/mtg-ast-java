package org.magic.api.ast.interfaces.visitors;

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

public abstract class AbstractVisitor<T> implements AbilityVisitor<T> {

	@Override
	public T visit(KeywordsAbility ability) {
		
		return null;
	}

	@Override
	public T visit(ActivatedAbility ability) {
		
		return null;
	}

	@Override
	public T visit(TriggeredAbility ability) {
		
		return null;
	}

	@Override
	public T visit(StaticAbility ability) {
		
		return null;
	}

	@Override
	public T visit(ModalAbility ability) {
		
		return null;
	}

	@Override
	public T visit(ReplacementEffectAbility ability) {
		
		return null;
	}

	@Override
	public T visit(ContinuousModifierAbility ability) {
		
		return null;
	}

	@Override
	public T visit(WordAbility ability) {
		
		return null;
	}

	@Override
	public T visit(SagaAbility ability) {
		
		return null;
	}

	@Override
	public T visit(PlaneswalkerAbility ability) {
		
		return null;
	}

	
}
