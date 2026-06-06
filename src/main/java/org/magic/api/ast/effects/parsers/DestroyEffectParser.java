package org.magic.api.ast.effects.parsers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.magic.api.ast.effects.DestroyTargetEffect;
import org.magic.api.ast.effects.EffectNode;
import org.magic.api.ast.interfaces.EffectParser;
import org.magic.api.ast.selectors.factory.TargetSelectorFactory;

public class DestroyEffectParser
implements EffectParser {

private static final Pattern PATTERN =
    Pattern.compile(
            "^Destroy\\s+(.+)",
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

return new DestroyTargetEffect(TargetSelectorFactory.INSTANCE.parse(matcher.group(1).trim()));
}
}