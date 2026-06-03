package org.magic.api.ast.effects.parsers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.magic.api.ast.effects.DrawCardsEffect;
import org.magic.api.ast.effects.EffectNode;
import org.magic.api.ast.interfaces.EffectParser;

public class DrawEffectParser
implements EffectParser {

private static final Pattern PATTERN =
    Pattern.compile(
            "^Draw\\s+(a|\\d+)\\s+card",
            Pattern.CASE_INSENSITIVE);

@Override
public boolean supports(String text) {
return PATTERN.matcher(text).find();
}

@Override
public EffectNode parse(String text) {

Matcher matcher =
        PATTERN.matcher(text);

matcher.find();

String amount =
        matcher.group(1);

return new DrawCardsEffect(
        "a".equalsIgnoreCase(amount)
                ? 1
                : Integer.parseInt(amount)
);
}
}