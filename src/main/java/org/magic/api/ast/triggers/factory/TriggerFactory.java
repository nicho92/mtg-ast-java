package org.magic.api.ast.triggers.factory;

import java.util.Set;

import org.magic.api.ast.selectors.factory.TargetSelectorFactory;
import org.magic.api.ast.triggers.Trigger;
import org.magic.api.ast.triggers.TriggerType;

public final class TriggerFactory {

	private TriggerFactory() {
	}

	public static final TriggerFactory INSTANCE = new TriggerFactory();

	public Trigger parse(String text) {

		var lower = text.toLowerCase();

		var locator = TargetSelectorFactory.INSTANCE.parse(text);

		if (lower.contains("enters the battlefield"))
			return new Trigger(locator, TriggerType.ENTERS_BATTLEFIELD);

		if (lower.contains("dies"))
			return new Trigger(locator,TriggerType.DIES);

		if (lower.contains("beginning of your upkeep"))
			return new Trigger(null,TriggerType.BEGINNING_OF_UPKEEP);

		if (lower.contains("beginning of your end step") || lower.contains("beginning of the end step")|| lower.contains("beginning of each end step"))
			return new Trigger(null,TriggerType.BEGINNING_OF_END_STEP);
		
		if (lower.contains("draws a card") || lower.contains("draw a card"))
			return new Trigger(locator,TriggerType.DRAW_CARD);

		if (lower.contains("discards") || lower.contains("discard"))
			return new Trigger(locator,TriggerType.DISCARD_CARD);

		if (lower.contains("gains life") || lower.contains("gain life"))
			return new Trigger(locator,TriggerType.GAIN_LIFE);

		if (Set.of("lose life", "loses life", "loose life", "looses life").stream().anyMatch(lower::contains))
			return new Trigger(locator,TriggerType.LOSE_LIFE);

		if (lower.contains("attacks") || lower.contains("attack"))
			return new Trigger(locator,TriggerType.ATTACKS);

		if (lower.contains("blocks") || lower.contains("block") || lower.contains("blocked"))
			return new Trigger(locator,TriggerType.BLOCKS);

		if (lower.contains("put into a graveyard") || lower.contains("destroy"))
			return new Trigger(locator,TriggerType.DESTROY);

		if (lower.contains("cast this spell"))
			return new Trigger(locator,TriggerType.SPELL_CAST);

		return new Trigger(null,TriggerType.UNKNOW);
	}
}