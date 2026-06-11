package org.magic.api.ast.costs;

import java.util.List;

import org.magic.api.ast.costs.model.ManaSymbol;
import org.magic.api.ast.interfaces.CostNode;

public record ManaValue(List<ManaSymbol> mana) implements CostNode { 
	
	@Override
	public final String toString() {
		return mana.toString();
	}
	
}