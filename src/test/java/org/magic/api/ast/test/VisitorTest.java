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
[+2]: Artifact, instant, and sorcery spells you cast this turn cost {2} less to cast. You gain 2 life.
[+1]: Draw two cards, then discard a card.
[0]: Create two 1/1 colorless Soldier artifact creature tokens.
[−X]: Exile X nonland permanent.
[−6]: You get an emblem with "At the beginning of combat on your turn, create a 1/1 white Soldier creature token, then put a +1/+1 counter on each creature you control.".
[−10]: You get an emblem with "At the beginning of your end step, create three 1/1 white Cat creature tokens with lifelink.".
					""";
		
			
			new OracleParser().parse("Urza", s)
			.getAbilities()
			.stream()
			.map(ab -> ab.accept(new EffectCollector()))
			.filter(Objects::nonNull)
			.forEach(System.out::println);
			
			System.out.println("=================");
			
			OracleParser.toFacade("Urza", s).getPlaneswalkerAbilities().forEach(pa->{
				System.out.println(pa.loyalty() + " " + pa.effects());
			});
	}
	
}