package org.magic.api.ast.effects;

import org.magic.api.ast.costs.model.DurationType;
import org.magic.api.ast.interfaces.EffectNode;
import org.magic.api.ast.interfaces.SelectorNode;

public record PTChangeEffect(String text, SelectorNode target, String powerChange,String toughnessChange,DurationType until) implements EffectNode {
	
	
	Integer powerAsInt(){
		return Integer.parseInt(powerChange.replace('+', ' ').trim());
	}
	
	Integer toughnessAsInt() {
		return Integer.parseInt(toughnessChange.replace('+', ' ').trim());
	}
	
	
	
	
}
