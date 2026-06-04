package org.magic.api.ast.abilities.parsers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.Test;
import org.magic.api.ast.abilities.ContinuousModifierAbility;
import org.magic.api.ast.abilities.ModalAbility;
import org.magic.api.ast.abilities.ReplacementEffectAbility;
import org.magic.api.ast.effects.DestroyTargetEffect;
import org.magic.api.ast.effects.DrawCardsEffect;
import org.magic.api.ast.effects.ExileEffect;
import org.magic.api.ast.engine.OracleParser;
import org.magic.api.ast.modifiers.PowerToughnessModifier;
import org.magic.api.ast.selectors.CreatureSelector;

class ModernAbilityParserTest {

    private final OracleParser parser = new OracleParser();

    @Test
    void parsesModalAbilitiesAsASingleAbilityBlock() {

        var ast = parser.parse(
                "Test Card",
                """
                Choose one —
                • Destroy target creature.
                • Draw a card.
                """
        );

        var ability = assertInstanceOf(ModalAbility.class, ast.getAbilities().getFirst());

        assertEquals(1, ast.getAbilities().size());
        assertEquals(1, ability.choiceConstraint().minimum());
        assertEquals(1, ability.choiceConstraint().maximum());
        assertEquals(2, ability.modes().size());
        assertInstanceOf(DestroyTargetEffect.class, ability.modes().get(0).effects().getFirst());
        assertInstanceOf(DrawCardsEffect.class, ability.modes().get(1).effects().getFirst());
    }

    @Test
    void parsesReplacementEffects() {

        var ast = parser.parse("Test Card", "If a creature would die, exile it instead.");

        var ability = assertInstanceOf(ReplacementEffectAbility.class, ast.getAbilities().getFirst());

        assertEquals("a creature would die", ability.replacedEvent());
        assertEquals(1, ability.replacementEffects().size());
        assertInstanceOf(ExileEffect.class, ability.replacementEffects().getFirst());
    }

    @Test
    void parsesPowerToughnessContinuousModifiers() {

        var ast = parser.parse("Test Card", "Creatures you control get +1/+1.");

        var ability = assertInstanceOf(ContinuousModifierAbility.class, ast.getAbilities().getFirst());

        var selector = assertInstanceOf(CreatureSelector.class, ability.affectedObjects());
        assertEquals(true, selector.controlledByYou());

        var modifier = assertInstanceOf(PowerToughnessModifier.class, ability.modifier());
        assertEquals(1, modifier.powerDelta());
        assertEquals(1, modifier.toughnessDelta());
    }
}
