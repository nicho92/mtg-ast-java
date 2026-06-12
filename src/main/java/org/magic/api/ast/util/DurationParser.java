package org.magic.api.ast.util;

import org.magic.api.ast.costs.model.DurationType;

public final class DurationParser {

	private DurationParser() {
	}

	public static DurationType parse(String text) {

		return switch (text.trim().toLowerCase()) {
		case "until end of turn"->DurationType.END_OF_TURN;
		case "this turn"->DurationType.THIS_TURN;
		case "until end of combat"->DurationType.END_OF_COMBAT;
		case "until your next turn"->DurationType.YOUR_NEXT_TURN;
		case "until the end of your next turn"->DurationType.END_OF_YOUR_NEXT_TURN;
		case "until your next upkeep"->DurationType.YOUR_NEXT_UPKEEP;
		case "until your next end step"->DurationType.YOUR_NEXT_END_STEP;
		case "until your next combat"->DurationType.YOUR_NEXT_COMBAT;
		default ->DurationType.CUSTOM;
		};
	}
}
