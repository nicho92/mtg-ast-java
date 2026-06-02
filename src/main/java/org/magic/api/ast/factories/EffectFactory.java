package org.magic.api.ast.factories;

import java.util.List;

import org.magic.api.ast.effects.EffectNode;
import org.magic.api.ast.effects.UnknownEffect;
import org.magic.api.ast.parser.*;
import org.magic.api.ast.parser.interfaces.EffectParser;

public class EffectFactory {

    private final List<EffectParser> parsers;

    public EffectFactory() {

        parsers = List.of(
                new DrawEffectParser(),
                new GainLifeEffectParser(),
                new AddManaEffectParser(),
                new DamageEffectParser(),
                new DestroyEffectParser(),
                new CreateTokenEffectParser()
        );
    }

    public EffectNode parse(String text) {

        for (var parser : parsers) {

            if (parser.supports(text)) {
                return parser.parse(text);
            }
        }

        return new UnknownEffect(text);
    }
}