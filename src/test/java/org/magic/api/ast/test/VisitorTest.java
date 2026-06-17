package org.magic.api.ast.test;

import java.util.Objects;

import org.junit.jupiter.api.Test;
import org.magic.api.ast.engine.OracleParser;
import org.magic.api.ast.visitor.impl.EffectCollector;

class VisitorTest {
	
	@Test
	 void test()
	 {
		
			var s = """
Whenever you draw a card, target opponent puts the top two cards of his or her library into his or her graveyard.
Each opponent mills four cards.
					""";
		
			
			new OracleParser().parse("Urza", s)
			.getAbilities()
			.stream()
			.map(ab -> ab.accept(new EffectCollector()))
			.filter(Objects::nonNull)
			.forEach(System.out::println);
			
			System.out.println("=================");
			
			OracleParser.toFacade("Urza", s).getAllAbilities().forEach(pa->{
				System.out.println(pa);
			});
	}
	
}