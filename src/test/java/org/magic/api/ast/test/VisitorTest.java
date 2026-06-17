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
[+1]: Put a +1/+1 counter on up to one target creature. It gains indestructible until end of turn.
[−2]: Whenever one or more nontoken creatures attack this turn, create that many 1/1 white Soldier creature tokens that are tapped and attacking.
[−6]: You get an emblem with "At the beginning of combat on your turn, create a 1/1 white Soldier creature token, then put a +1/+1 counter on each creature you control.".

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