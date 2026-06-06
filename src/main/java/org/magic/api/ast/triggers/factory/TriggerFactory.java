package org.magic.api.ast.triggers.factory;

import org.magic.api.ast.selectors.factory.TargetSelectorFactory;
import org.magic.api.ast.triggers.AttacksTrigger;
import org.magic.api.ast.triggers.BlocksTrigger;
import org.magic.api.ast.triggers.DiesTrigger;
import org.magic.api.ast.triggers.DiscardCardTrigger;
import org.magic.api.ast.triggers.DrawCardTrigger;
import org.magic.api.ast.triggers.EndStepTrigger;
import org.magic.api.ast.triggers.EntersBattlefieldTrigger;
import org.magic.api.ast.triggers.GainLifeTrigger;
import org.magic.api.ast.triggers.LoseLifeTrigger;
import org.magic.api.ast.triggers.TriggerNode;
import org.magic.api.ast.triggers.UnknownTrigger;
import org.magic.api.ast.triggers.UpkeepTrigger;

public final class TriggerFactory {

	private TriggerFactory() {
	}

	public static final TriggerFactory INSTANCE = new TriggerFactory();
	
	public TriggerNode parse(String text) {

		var lower = text.toLowerCase();

		var locator = TargetSelectorFactory.INSTANCE.parse(text);
		
		if (lower.contains("enters the battlefield")) 
			return new EntersBattlefieldTrigger(locator);

		if (lower.contains("dies")) 
			return new DiesTrigger(locator);

		if (lower.contains("beginning of your upkeep")) 
			return new UpkeepTrigger();

		if (lower.contains("beginning of your end step") || lower.contains("beginning of the end step") || lower.contains("beginning of each end step"))
			return new EndStepTrigger();

		if (lower.contains("draws a card") || lower.contains("draw a card"))
			return new DrawCardTrigger(locator);

		if (lower.contains("discards") || lower.contains("discard"))
			return new DiscardCardTrigger(locator);

		if (lower.contains("gains life") || lower.contains("gain life"))
			return new GainLifeTrigger(locator);

		if (lower.contains("loses life") || lower.contains("lose life") || lower.contains("looses life") || lower.contains("loose life"))
			return new LoseLifeTrigger(locator);

		if (lower.contains("attacks") || lower.contains("attack"))
			return new AttacksTrigger(locator);

		if (lower.contains("blocks") || lower.contains("block") || lower.contains("blocked"))
			return new BlocksTrigger(locator);

		return new UnknownTrigger(text);
	}
}