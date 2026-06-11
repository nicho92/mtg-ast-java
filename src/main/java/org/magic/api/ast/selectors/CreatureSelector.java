package org.magic.api.ast.selectors;

import org.magic.api.ast.interfaces.SelectorNode;

public record CreatureSelector(boolean target, boolean another, boolean controlledByYou) implements SelectorNode {
	
	
	
}