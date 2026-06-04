package org.magic.api.ast.effects.parsers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.magic.api.ast.effects.EffectNode;
import org.magic.api.ast.effects.ExileEffect;
import org.magic.api.ast.factories.TargetSelectorFactory;
import org.magic.api.ast.interfaces.EffectParser;

public class ExileEffectParser implements EffectParser {

    private static final Pattern PATTERN = Pattern.compile(
            "^Exile\\s+(.+)$",
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

        return new ExileEffect(
                TargetSelectorFactory.INSTANCE.parse(matcher.group(1).trim())
        );
    }
}
