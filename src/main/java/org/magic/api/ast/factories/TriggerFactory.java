package org.magic.api.ast.factories;

import org.magic.api.ast.triggers.DiesTrigger;
import org.magic.api.ast.triggers.EntersBattlefieldTrigger;
import org.magic.api.ast.triggers.TriggerNode;
import org.magic.api.ast.triggers.UnknownTrigger;
import org.magic.api.ast.triggers.UpkeepTrigger;

public final class TriggerFactory {

    private TriggerFactory() {}

    public static TriggerNode parse(
            String text) {

        String lower =
                text.toLowerCase();

        if (lower.contains(
                "enters the battlefield")) {

            return new EntersBattlefieldTrigger(
                    text);
        }

        if (lower.contains("dies")) {

            return new DiesTrigger(text);
        }

        if (lower.contains(
                "beginning of your upkeep")) {

            return new UpkeepTrigger();
        }

        return new UnknownTrigger(text);
    }
}