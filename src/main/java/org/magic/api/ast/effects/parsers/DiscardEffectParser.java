package org.magic.api.ast.effects.parsers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.magic.api.ast.effects.DiscardCardsEffect;
import org.magic.api.ast.effects.EffectNode;
import org.magic.api.ast.interfaces.EffectParser;
import org.magic.api.ast.selectors.factory.TargetSelectorFactory;
import org.magic.api.ast.util.AmountParser;

public class DiscardEffectParser implements EffectParser {

    private static final Pattern PATTERN = Pattern.compile(
            "^(You|Each opponent|Target player|Each player)\\s+discards?\\s+(a|an|one|two|three|four|five|six|seven|eight|nine|ten|\\d+)\\s+cards?",
            Pattern.CASE_INSENSITIVE
    );

    @Override
    public boolean supports(String text) {
        return PATTERN.matcher(text).find();
    }

    @Override
    public EffectNode parse(String text) {

        Matcher matcher = PATTERN.matcher(text);

        matcher.find();

        return new DiscardCardsEffect(
                TargetSelectorFactory.INSTANCE.parse(matcher.group(1).trim()),
                AmountParser.parse(matcher.group(2))
        );
    }
}
