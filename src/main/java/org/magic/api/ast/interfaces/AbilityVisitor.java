package org.magic.api.ast.interfaces;

import org.magic.api.ast.abilities.*;

/**
 * Visitor interface for traversing and processing ability nodes.
 * Implements the Visitor design pattern to avoid instanceof checks and casting.
 * 
 * Usage example:
 * <pre>
 * AbilityVisitor<String> visitor = new AbilityDescriber();
 * for (AbilityNode ability : card.getAbilities()) {
 *     String description = ability.accept(visitor);
 *     System.out.println(description);
 * }
 * </pre>
 * 
 * @param <T> the return type of the visitor operations
 */
public interface AbilityVisitor<T> {
    
    /**
     * Visit a KeywordAbility node.
     * 
     * @param ability the keyword ability to visit
     * @return the result of visiting this ability
     */
    T visit(KeywordAbility ability);
    
    /**
     * Visit a KeywordGroupAbility node.
     * 
     * @param ability the keyword group ability to visit
     * @return the result of visiting this ability
     */
    T visit(KeywordGroupAbility ability);
    
    /**
     * Visit an ActivatedAbility node.
     * 
     * @param ability the activated ability to visit
     * @return the result of visiting this ability
     */
    T visit(ActivatedAbility ability);
    
    /**
     * Visit a TriggeredAbility node.
     * 
     * @param ability the triggered ability to visit
     * @return the result of visiting this ability
     */
    T visit(TriggeredAbility ability);
    
    /**
     * Visit a StaticAbility node.
     * 
     * @param ability the static ability to visit
     * @return the result of visiting this ability
     */
    T visit(StaticAbility ability);
    
    /**
     * Visit a ModalAbility node.
     * 
     * @param ability the modal ability to visit
     * @return the result of visiting this ability
     */
    T visit(ModalAbility ability);
    
    /**
     * Visit a ReplacementEffectAbility node.
     * 
     * @param ability the replacement effect ability to visit
     * @return the result of visiting this ability
     */
    T visit(ReplacementEffectAbility ability);
    
    /**
     * Visit a ContinuousModifierAbility node.
     * 
     * @param ability the continuous modifier ability to visit
     * @return the result of visiting this ability
     */
    T visit(ContinuousModifierAbility ability);

    
    /**
     * Visit a WordAbility node.
     * 
     * @param ability the word ability to visit
     * @return the result of visiting this ability
     */
	T visit(WordAbility wordAbility);

	  /**
     * Visit a SagaAbility node.
     * 
     * @param ability the saga ability to visit
     * @return the result of visiting this ability
     */
	T visit(SagaAbility sagaAbility);

	  /**
     * Visit a PlaneswalkerAbility node.
     * 
     * @param ability the ¨Planeswalker ability to visit
     * @return the result of visiting this ability
     */
	T visit(PlaneswalkerAbility planeswalkerAbility);
}
