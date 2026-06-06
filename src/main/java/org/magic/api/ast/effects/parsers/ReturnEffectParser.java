package org.magic.api.ast.effects.parsers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.magic.api.ast.effects.EffectNode;
import org.magic.api.ast.effects.ReturnEffect;
import org.magic.api.ast.interfaces.EffectParser;
import org.magic.api.ast.selectors.factory.TargetSelectorFactory;

public class ReturnEffectParser implements EffectParser {

    private static final Pattern PATTERN = Pattern.compile(
            "^Return\\s+(.+?)\\s+to\\s+(.+)$",
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

        return new ReturnEffect(
                TargetSelectorFactory.INSTANCE.parse(matcher.group(1).trim()),
                matcher.group(2).trim()
        );
    }
}
