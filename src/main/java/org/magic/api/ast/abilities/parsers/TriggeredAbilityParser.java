package org.magic.api.ast.abilities.parsers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.magic.api.ast.abilities.AbilityNode;
import org.magic.api.ast.abilities.TriggeredAbility;
import org.magic.api.ast.effects.parsers.EffectSequenceParser;
import org.magic.api.ast.factories.TriggerFactory;
import org.magic.api.ast.interfaces.AbilityParser;
import org.magic.api.ast.triggers.TriggerNode;

public class TriggeredAbilityParser
implements AbilityParser {

private static final Pattern PATTERN =
    Pattern.compile(
            "^(When|Whenever|At)\\s+(.*?),\\s*(.*)$",
            Pattern.CASE_INSENSITIVE);

@Override
public boolean supports(String text) {
return PATTERN.matcher(text).matches();
}

@Override
public AbilityNode parse(String text) {

Matcher matcher =
        PATTERN.matcher(text);

matcher.find();

String triggerText =
        matcher.group(2);

String effectText =
        matcher.group(3);

TriggerNode trigger =
        TriggerFactory.INSTANCE.parse(triggerText);

return new TriggeredAbility(
        trigger,
        new EffectSequenceParser().parse(effectText)
);
}
}