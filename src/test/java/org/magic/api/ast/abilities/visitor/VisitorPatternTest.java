package org.magic.api.ast.abilities.visitor;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.magic.api.ast.abilities.*;
import org.magic.api.ast.abilities.visitor.impl.*;
import org.magic.api.ast.costs.ManaCost;
import org.magic.api.ast.effects.DrawCardsEffect;
import org.magic.api.ast.engine.CardAst;
import org.magic.api.ast.engine.OracleParser;

/**
 * Unit tests demonstrating the usage of the Visitor Pattern
 * for processing MTG card abilities.
 */
@DisplayName("Visitor Pattern Tests")
class VisitorPatternTest {
    
    private CardAst card;
    private EffectCollector effectCollector;
    private CostCollector costCollector;
    private TriggerCollector triggerCollector;
    private AbilityDescriber describer;
    
    @BeforeEach
    void setUp() {
        // Parse a sample card with multiple ability types
        String oracleText = """
            Flying, Ward {2}
            
            Whenever another creature dies, draw a card.
            
            {T}: Add {G}.
            """;
        
        card = new OracleParser().parse("Test Card", oracleText);
        
        // Initialize visitors
        effectCollector = new EffectCollector();
        costCollector = new CostCollector();
        triggerCollector = new TriggerCollector();
        describer = new AbilityDescriber();
    }
    
    @Test
    @DisplayName("Should collect all effects using EffectCollector visitor")
    void testEffectCollector() {
        List<EffectNode> allEffects = card.getAbilities().stream()
            .map(ability -> ability.accept(effectCollector))
            .flatMap(List::stream)
            .collect(Collectors.toList());
        
        // Should have at least one draw effect from the triggered ability
        long drawCount = allEffects.stream()
            .filter(e -> e instanceof DrawCardsEffect)
            .count();
        
        assertTrue(drawCount > 0, "Should find at least one draw effect");
    }
    
    @Test
    @DisplayName("Should collect all costs using CostCollector visitor")
    void testCostCollector() {
        List<CostNode> allCosts = card.getAbilities().stream()
            .map(ability -> ability.accept(costCollector))
            .flatMap(List::stream)
            .collect(Collectors.toList());
        
        // The activated ability "{T}: Add {G}" has a tap cost
        assertTrue(!allCosts.isEmpty(), "Should find at least one cost");
    }
    
    @Test
    @DisplayName("Should collect all triggers using TriggerCollector visitor")
    void testTriggerCollector() {
        List<TriggerNode> allTriggers = card.getAbilities().stream()
            .map(ability -> ability.accept(triggerCollector))
            .flatMap(List::stream)
            .collect(Collectors.toList());
        
        // The triggered ability "Whenever another creature dies..." should be collected
        assertTrue(!allTriggers.isEmpty(), "Should find at least one trigger");
    }
    
    @Test
    @DisplayName("Should generate descriptions for all abilities")
    void testAbilityDescriber() {
        List<String> descriptions = card.getAbilities().stream()
            .map(ability -> ability.accept(describer))
            .collect(Collectors.toList());
        
        // Should have descriptions for all abilities
        assertEquals(card.getAbilities().size(), descriptions.size());
        
        // All descriptions should be non-empty
        assertTrue(descriptions.stream().allMatch(d -> !d.isEmpty()));
        
        // Print for manual verification
        descriptions.forEach(System.out::println);
    }
    
    @Test
    @DisplayName("Visitor pattern should be type-safe without casting")
    void testTypeSafety() {
        // This test demonstrates the type-safety of the visitor pattern
        // No instanceof checks or casting needed
        
        AbilityVisitor<Integer> abilitySizer = new AbilityVisitor<>() {
            @Override
            public Integer visit(KeywordAbility ability) { return 1; }
            
            @Override
            public Integer visit(KeywordGroupAbility ability) {
                return ability.keywords().size();
            }
            
            @Override
            public Integer visit(ActivatedAbility ability) {
                return ability.costs().size() + ability.effects().size();
            }
            
            @Override
            public Integer visit(TriggeredAbility ability) {
                return 1 + ability.effects().size();
            }
            
            @Override
            public Integer visit(StaticAbility ability) { return 1; }
            
            @Override
            public Integer visit(ModalAbility ability) { return ability.modes().size(); }
            
            @Override
            public Integer visit(ReplacementEffectAbility ability) {
                return ability.replacementEffects().size();
            }
            
            @Override
            public Integer visit(ContinuousModifierAbility ability) { return 1; }
        };
        
        // All abilities can be processed without casting
        card.getAbilities().forEach(ability -> {
            Integer size = ability.accept(abilitySizer);
            assertTrue(size > 0);
        });
    }
    
    @Test
    @DisplayName("Multiple visitors should work independently")
    void testMultipleVisitors() {
        EffectCollector effects = new EffectCollector();
        CostCollector costs = new CostCollector();
        
        for (AbilityNode ability : card.getAbilities()) {
            List<EffectNode> abilityEffects = ability.accept(effects);
            List<CostNode> abilityCosts = ability.accept(costs);
            
            // Each visitor operates independently
            assertNotNull(abilityEffects);
            assertNotNull(abilityCosts);
        }
    }
}
