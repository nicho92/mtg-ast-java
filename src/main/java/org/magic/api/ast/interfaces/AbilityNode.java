package org.magic.api.ast.interfaces;

import org.magic.api.ast.abilities.ActivatedAbility;
import org.magic.api.ast.abilities.ContinuousModifierAbility;
import org.magic.api.ast.abilities.KeywordsAbility;
import org.magic.api.ast.abilities.ModalAbility;
import org.magic.api.ast.abilities.PlaneswalkerAbility;
import org.magic.api.ast.abilities.ReplacementEffectAbility;
import org.magic.api.ast.abilities.SagaAbility;
import org.magic.api.ast.abilities.SpellAbility;
import org.magic.api.ast.abilities.StaticAbility;
import org.magic.api.ast.abilities.TriggeredAbility;
import org.magic.api.ast.abilities.WordAbility;
import org.magic.api.ast.interfaces.visitors.AbilityVisitor;

/**
 * Base interface for all ability nodes in the MTG AST. Implements the Visitor
 * pattern to support polymorphic operations.
 */
public sealed interface AbilityNode 
permits ActivatedAbility, ContinuousModifierAbility, KeywordsAbility, ModalAbility, PlaneswalkerAbility, ReplacementEffectAbility, SagaAbility, SpellAbility, StaticAbility, TriggeredAbility,WordAbility {

	/**
	 * Accepts a visitor to process this ability node. This method implements the
	 * Visitor design pattern.
	 * 
	 * @param <T>     the return type of the visitor
	 * @param visitor the visitor to accept
	 * @return the result of the visitor's processing
	 */
	<T> T accept(AbilityVisitor<T> visitor);
	
	
	String text();
	
}
