package org.magic.api.ast.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.magic.api.ast.effects.DealDamageEffect;
import org.magic.api.ast.effects.EffectNode;
import org.magic.api.ast.parser.interfaces.EffectParser;

public class DamageEffectParser
implements EffectParser {

private static final Pattern PATTERN =
    Pattern.compile(
            "deal\\s+(\\d+)\\s+damage\\s+to\\s+(.+)",
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

return new DealDamageEffect(
        Integer.parseInt(
                matcher.group(1)),
        matcher.group(2)
);
}
}