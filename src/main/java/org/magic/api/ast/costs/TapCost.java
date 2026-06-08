package org.magic.api.ast.costs;

import org.magic.api.ast.interfaces.CostNode;

public final class TapCost implements CostNode {

	public enum TYPE { TAP,UNTAP}

	private TYPE type;
	
	public TapCost(TYPE t) {
		this.type = t;
	}
	
	public TYPE getType() {
		return type;
	}
	

	@Override
	public String toString() {
		return type.name().toLowerCase();
	}
	
	public static TapCost tap() {
		return new TapCost(TYPE.TAP);
	}

	public static TapCost untap() {
		return new TapCost(TYPE.UNTAP);
	}
	
	

}