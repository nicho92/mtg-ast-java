package org.magic.api.ast.effects.parsers;

import org.magic.api.ast.effects.LoseLifeEffect;
import org.magic.api.ast.factories.SelectorFactory;
import org.magic.api.ast.interfaces.EffectNode;
import org.magic.api.ast.interfaces.parsers.AbstractParser;
import org.magic.api.ast.interfaces.parsers.EffectParser;
import org.magic.api.ast.util.AmountParser;

public class LoseLifeEffectParser extends AbstractParser<EffectNode> implements EffectParser {
    @Override
    protected String getPattern() {
        return "^(Target\\s+opponent|You|A\\s+player)\\s+loses?\\s+(\\d+|a|two|three|four)\\s+life\\.?$";
    }
    @Override
    public EffectNode parse(String text) {
        var matcher = match(text);
        var target = SelectorFactory.INSTANCE.parse(matcher.group(1).trim());
        var amount = AmountParser.parse(matcher.group(2).trim());
        return new LoseLifeEffect(text, target, amount);
    }
}