package org.magic.api.ast.effects;

import org.magic.api.ast.interfaces.EffectNode;
import org.magic.api.ast.interfaces.SelectorNode;

public record PTChangeEffect(SelectorNode target, String powerChange,String toughnessChange,String until) implements EffectNode {
	
	
	Integer powerAsInt(){
		return Integer.parseInt(powerChange);
	}
	
	Integer toughnessAsInt() {
		return Integer.parseInt(toughnessChange);
	}
	
	
	
}
