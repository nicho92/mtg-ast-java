package org.magic.api.ast.factories;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.magic.api.ast.effects.AddManaEffect;
import org.magic.api.ast.effects.DrawCardsEffect;
import org.magic.api.ast.effects.EffectNode;
import org.magic.api.ast.effects.GainLifeEffect;
import org.magic.api.ast.effects.UnknownEffect;

public final class EffectFactory {

    private static final Pattern DRAW =
            Pattern.compile(
                    "draw (a|\\d+) card",
                    Pattern.CASE_INSENSITIVE);

    private static final Pattern GAIN_LIFE =
            Pattern.compile(
                    "gain (\\d+) life",
                    Pattern.CASE_INSENSITIVE);

    private static final Pattern ADD_MANA =
            Pattern.compile(
                    "add \\{([WUBRG])}",
                    Pattern.CASE_INSENSITIVE);

    private EffectFactory() {}

    public static EffectNode parse(
            String text) {

        Matcher draw =
                DRAW.matcher(text);

        if (draw.find()) {

            String amount =
                    draw.group(1);

            return new DrawCardsEffect(
                    "a".equalsIgnoreCase(amount)
                            ? 1
                            : Integer.parseInt(amount));
        }

        Matcher life =
                GAIN_LIFE.matcher(text);

        if (life.find()) {

            return new GainLifeEffect(
                    Integer.parseInt(
                            life.group(1)));
        }

        Matcher mana =
                ADD_MANA.matcher(text);

        if (mana.find()) {

            return new AddManaEffect(
                    mana.group(1));
        }

        return new UnknownEffect(text);
    }
}