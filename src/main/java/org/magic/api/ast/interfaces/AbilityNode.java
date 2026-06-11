package org.magic.api.ast.interfaces;

import org.magic.api.ast.interfaces.visitors.AbilityVisitor;

/**
 * Base interface for all ability nodes in the MTG AST. Implements the Visitor
 * pattern to support polymorphic operations.
 */
public interface AbilityNode {

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
