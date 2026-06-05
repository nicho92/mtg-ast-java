package org.magic.api.ast.test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.magic.api.ast.engine.OracleParser;

class LoadOracles {

	
	
	public List<String> loadOracle() throws Exception
	{
		
		var content =  Files.readString(Path.of(LoadOracles.class.getResource("/oracles.txt").toURI()));
		
		return Arrays.stream(content.split("(?m)^===\\s*$"))
	                .map(String::trim)
	                .filter(s -> !s.isEmpty())
	                .toList();
	}
	
	
	@Test
	 void test() throws Exception
	{
		var parser =  new OracleParser();
		
		for(var s : loadOracle())
		{
			System.out.println(s);
			System.out.println();
			
			parser.parse("card", s).getAbilities().forEach(a->{
				System.out.println(a);
			});
			
			System.out.println("=========");
		}
	}

	@Test
	void testAbilityWords() {
		var parser = new OracleParser();
		var oracleText = "Landfall — Whenever a land enters the battlefield under your control, you gain 2 life.";
		var ast = parser.parse("Landfall Card", oracleText);
		var abilities = ast.getAbilities();
		
		org.junit.jupiter.api.Assertions.assertEquals(1, abilities.size());
		org.junit.jupiter.api.Assertions.assertInstanceOf(org.magic.api.ast.abilities.AbilityWordAbility.class, abilities.get(0));
		
		var abilityWordNode = (org.magic.api.ast.abilities.AbilityWordAbility) abilities.get(0);
		org.junit.jupiter.api.Assertions.assertNotNull(abilityWordNode);
		org.junit.jupiter.api.Assertions.assertEquals("Landfall", abilityWordNode.abilityWord());
		
		org.junit.jupiter.api.Assertions.assertInstanceOf(org.magic.api.ast.abilities.TriggeredAbility.class, abilityWordNode.ability());
	}

	@Test
	void testNewTriggers() {
		var parser = new OracleParser();
		
		// End step trigger
		var endStepAst = parser.parse("Card", "At the beginning of your end step, draw a card.");
		var tEnd = ((org.magic.api.ast.abilities.TriggeredAbility) endStepAst.getAbilities().get(0)).trigger();
		org.junit.jupiter.api.Assertions.assertInstanceOf(org.magic.api.ast.triggers.EndStepTrigger.class, tEnd);

		// Draw trigger
		var drawAst = parser.parse("Card", "Whenever you draw a card, you gain 1 life.");
		var tDraw = ((org.magic.api.ast.abilities.TriggeredAbility) drawAst.getAbilities().get(0)).trigger();
		org.junit.jupiter.api.Assertions.assertInstanceOf(org.magic.api.ast.triggers.DrawCardTrigger.class, tDraw);

		// Discard trigger
		var discardAst = parser.parse("Card", "Whenever an opponent discards a card, they lose 2 life.");
		var tDiscard = ((org.magic.api.ast.abilities.TriggeredAbility) discardAst.getAbilities().get(0)).trigger();
		org.junit.jupiter.api.Assertions.assertInstanceOf(org.magic.api.ast.triggers.DiscardCardTrigger.class, tDiscard);

		// Gain life trigger
		var gainLifeAst = parser.parse("Card", "Whenever you gain life, draw a card.");
		var tGain = ((org.magic.api.ast.abilities.TriggeredAbility) gainLifeAst.getAbilities().get(0)).trigger();
		org.junit.jupiter.api.Assertions.assertInstanceOf(org.magic.api.ast.triggers.GainLifeTrigger.class, tGain);

		// Lose life trigger
		var loseLifeAst = parser.parse("Card", "Whenever an opponent loses life, put a +1/+1 counter on this creature.");
		var tLose = ((org.magic.api.ast.abilities.TriggeredAbility) loseLifeAst.getAbilities().get(0)).trigger();
		org.junit.jupiter.api.Assertions.assertInstanceOf(org.magic.api.ast.triggers.LoseLifeTrigger.class, tLose);

		// Attacks trigger
		var attackAst = parser.parse("Card", "Whenever a creature you control attacks, you gain 1 life.");
		var tAttack = ((org.magic.api.ast.abilities.TriggeredAbility) attackAst.getAbilities().get(0)).trigger();
		org.junit.jupiter.api.Assertions.assertInstanceOf(org.magic.api.ast.triggers.AttacksTrigger.class, tAttack);

		// Blocks trigger
		var blockAst = parser.parse("Card", "Whenever this creature blocks, draw a card.");
		var tBlock = ((org.magic.api.ast.abilities.TriggeredAbility) blockAst.getAbilities().get(0)).trigger();
		org.junit.jupiter.api.Assertions.assertInstanceOf(org.magic.api.ast.triggers.BlocksTrigger.class, tBlock);
	}
}
