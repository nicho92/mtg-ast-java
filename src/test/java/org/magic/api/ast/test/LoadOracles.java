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
		org.junit.jupiter.api.Assertions.assertEquals("Landfall", abilityWordNode.abilityWord());
		
		org.junit.jupiter.api.Assertions.assertInstanceOf(org.magic.api.ast.abilities.TriggeredAbility.class, abilityWordNode.ability());
	}
}
