package org.magic.api.ast.effects.parsers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.Test;
import org.magic.api.ast.abilities.SagaAbility;
import org.magic.api.ast.effects.DiscardCardsEffect;
import org.magic.api.ast.effects.ExileEffect;
import org.magic.api.ast.effects.PutCountersEffect;
import org.magic.api.ast.effects.ReturnEffect;
import org.magic.api.ast.effects.SacrificeEffect;
import org.magic.api.ast.engine.OracleParser;
import org.magic.api.ast.selectors.CreatureSelector;
import org.magic.api.ast.selectors.PlayerSelector;

class CommonEffectCoverageTest {

    private final OracleParser parser = new OracleParser();

    @Test
    void parsesCounterEffects() {

        var ast = parser.parse("Test Card", "I — Put two +1/+1 counters on target creature.");

        var ability = assertInstanceOf(SagaAbility.class, ast.getAbilities().getFirst());
        var effect = assertInstanceOf(PutCountersEffect.class, ability.effects().getFirst());

        assertEquals(2, effect.amount());
        assertEquals("+1/+1", effect.counterType());
        assertInstanceOf(CreatureSelector.class, effect.target());
    }

    @Test
    void parsesZoneMovementEffects() {

        var ast = parser.parse(
                "Test Card",
                "I — Exile target creature. Return target creature card from your graveyard to the battlefield under your control."
        );

        var ability = assertInstanceOf(SagaAbility.class, ast.getAbilities().getFirst());

        assertInstanceOf(ExileEffect.class, ability.effects().get(0));

        var returnEffect = assertInstanceOf(ReturnEffect.class, ability.effects().get(1));
        assertEquals("the battlefield under your control", returnEffect.destination());
    }

    @Test
    void parsesDiscardAndSacrificeEffects() {

        var ast = parser.parse(
                "Test Card",
                "I — Each opponent discards a card. Each opponent sacrifices a creature or planeswalker."
        );

        var ability = assertInstanceOf(SagaAbility.class, ast.getAbilities().getFirst());

        var discardEffect = assertInstanceOf(DiscardCardsEffect.class, ability.effects().get(0));
        assertEquals(1, discardEffect.amount());
        assertInstanceOf(PlayerSelector.class, discardEffect.player());

        var sacrificeEffect = assertInstanceOf(SacrificeEffect.class, ability.effects().get(1));
        assertInstanceOf(PlayerSelector.class, sacrificeEffect.player());
    }
}
