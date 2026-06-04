package org.magic.api.ast.effects.parsers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.Test;
import org.magic.api.ast.abilities.ActivatedAbility;
import org.magic.api.ast.abilities.SagaAbility;
import org.magic.api.ast.abilities.TriggeredAbility;
import org.magic.api.ast.effects.AddManaEffect;
import org.magic.api.ast.effects.DrawCardsEffect;
import org.magic.api.ast.effects.GainLifeEffect;
import org.magic.api.ast.effects.UnknownEffect;
import org.magic.api.ast.engine.OracleParser;

class EffectSequenceParserTest {

    private final OracleParser parser = new OracleParser();

    @Test
    void parsesEffectsSeparatedBySentenceBoundaries() {

        var ast = parser.parse(
                "Test Card",
                "Whenever another creature dies, draw a card. You gain 3 life."
        );

        var ability = assertInstanceOf(TriggeredAbility.class, ast.getAbilities().getFirst());

        assertEquals(2, ability.effects().size());
        assertInstanceOf(DrawCardsEffect.class, ability.effects().get(0));
        assertInstanceOf(GainLifeEffect.class, ability.effects().get(1));
    }

    @Test
    void parsesThenSeparatedEffects() {

        var ast = parser.parse("Test Card", "{T}: Add {G}, then draw a card.");

        var ability = assertInstanceOf(ActivatedAbility.class, ast.getAbilities().getFirst());

        assertEquals(2, ability.effects().size());
        assertEquals(new AddManaEffect("{G}"), ability.effects().get(0));
        assertEquals(new DrawCardsEffect(1), ability.effects().get(1));
    }

    @Test
    void preservesUnknownFragmentsInSequence() {

        var ast = parser.parse("Test Card", "I — Draw a card. Proliferate.");

        var ability = assertInstanceOf(SagaAbility.class, ast.getAbilities().getFirst());

        assertEquals(2, ability.effects().size());
        assertInstanceOf(DrawCardsEffect.class, ability.effects().get(0));
        assertEquals(new UnknownEffect("Proliferate"), ability.effects().get(1));
    }
}
