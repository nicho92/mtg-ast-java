package org.magic.api.ast.costs;

import java.util.List;

import org.magic.api.ast.costs.model.ManaSymbol;
import org.magic.api.ast.interfaces.CostNode;

public record ManaValue(String text,List<ManaSymbol> mana) implements CostNode { 
	
}