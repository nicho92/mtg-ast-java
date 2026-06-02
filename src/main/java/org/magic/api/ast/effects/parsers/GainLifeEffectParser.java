package org.magic.api.ast.effects.parsers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.magic.api.ast.effects.EffectNode;
import org.magic.api.ast.effects.GainLifeEffect;
import org.magic.api.ast.parser.interfaces.EffectParser;

public class GainLifeEffectParser
implements EffectParser {

private static final Pattern PATTERN =
    Pattern.compile(
            "^Gain\\s+(\\d+)\\s+life",
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

return new GainLifeEffect(
        Integer.parseInt(
                matcher.group(1))
);
}
}