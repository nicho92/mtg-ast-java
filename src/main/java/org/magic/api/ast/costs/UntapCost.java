package org.magic.api.ast.costs;

public final class UntapCost implements CostNode {

public static final UntapCost INSTANCE = new UntapCost();

private UntapCost() {}

@Override
	public String toString() {
		return "Untap";
	}

}