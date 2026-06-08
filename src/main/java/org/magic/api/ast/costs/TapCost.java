package org.magic.api.ast.costs;

public final class TapCost implements CostNode {

	public static final TapCost INSTANCE = new TapCost();

	private TapCost() {
	}

	@Override
	public String toString() {
		return "Tap";
	}

}