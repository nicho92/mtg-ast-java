package org.magic.api.ast.test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.magic.api.ast.engine.OracleParser;

public class LoadOracles {

	
	
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
	void testPlaneswalkerAbilities() {
		var parser = new OracleParser();
		var oracleText = """
				+1: Draw a card.
				0: Discard a card.
				−3: Destroy target creature.
				-X: Draw X cards.
				""";
		var ast = parser.parse("Liliana", oracleText);
		var abilities = ast.getAbilities();
		
		org.junit.jupiter.api.Assertions.assertEquals(4, abilities.size());
		
		org.junit.jupiter.api.Assertions.assertInstanceOf(org.magic.api.ast.abilities.PlaneswalkerAbility.class, abilities.get(0));
		var a0 = (org.magic.api.ast.abilities.PlaneswalkerAbility) abilities.get(0);
		org.junit.jupiter.api.Assertions.assertEquals("+1", a0.loyalty());
		
		org.junit.jupiter.api.Assertions.assertInstanceOf(org.magic.api.ast.abilities.PlaneswalkerAbility.class, abilities.get(1));
		var a1 = (org.magic.api.ast.abilities.PlaneswalkerAbility) abilities.get(1);
		org.junit.jupiter.api.Assertions.assertEquals("0", a1.loyalty());
		
		org.junit.jupiter.api.Assertions.assertInstanceOf(org.magic.api.ast.abilities.PlaneswalkerAbility.class, abilities.get(2));
		var a2 = (org.magic.api.ast.abilities.PlaneswalkerAbility) abilities.get(2);
		org.junit.jupiter.api.Assertions.assertEquals("−3", a2.loyalty());
		
		org.junit.jupiter.api.Assertions.assertInstanceOf(org.magic.api.ast.abilities.PlaneswalkerAbility.class, abilities.get(3));
		var a3 = (org.magic.api.ast.abilities.PlaneswalkerAbility) abilities.get(3);
		org.junit.jupiter.api.Assertions.assertEquals("-X", a3.loyalty());
	}
}
