package org.magic.api.ast.effects.parsers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.magic.api.ast.effects.EffectNode;
import org.magic.api.ast.effects.PutCountersEffect;
import org.magic.api.ast.interfaces.EffectParser;
import org.magic.api.ast.selectors.factory.TargetSelectorFactory;
import org.magic.api.ast.util.AmountParser;

public class PutCountersEffectParser implements EffectParser {

    private static final Pattern PATTERN = Pattern.compile(
            "^Put\\s+(a|an|one|two|three|four|five|six|seven|eight|nine|ten|\\d+)\\s+(.+?)\\s+counters?\\s+on\\s+(.+)$",
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

        return new PutCountersEffect(
                AmountParser.parse(matcher.group(1)),
                matcher.group(2).trim(),
                TargetSelectorFactory.INSTANCE.parse(matcher.group(3).trim())
        );
    }
}
