package org.magic.api.ast.test;

import java.util.Objects;

import org.junit.jupiter.api.Test;
import org.magic.api.ast.abilities.visitor.impl.TriggerCollector;
import org.magic.api.ast.engine.OracleParser;

class VisitorTest {
	
	@Test
	 void test()
	 {
		
			var s = """
					When Emrakul is put into a graveyard from anywhere, its owner shuffles their graveyard into their library.
					""";
		
			new OracleParser().parse("Emrakul, the Aeon's end", s)
			.getAbilities()
			.stream()
			.map(ab -> ab.accept(new TriggerCollector()))
			.filter(Objects::nonNull)
			.forEach(System.out::println);
	}
	
}