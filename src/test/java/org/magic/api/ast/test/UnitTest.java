package org.magic.api.ast.test;

import org.junit.jupiter.api.Test;
import org.magic.api.ast.engine.OracleParser;

class UnitTest {
	
	@Test
	 void test()
	 {
			var s = """
						Flying
						When this creature enters, draw a card.
						{1}{U}:  Create a 1/1 colorless Shapeshifter creature token with changeling.
					""";
			
//			new OracleParser().parse("Urza", s)
//						.getAbilities()
//						.stream()
//						.map(ab -> ab.accept(new EffectCollector()))
//						.filter(Objects::nonNull)
//						.forEach(System.out::println);
			
			var f = OracleParser.toFacade("Urza", s);
					
			f.getKeywordsAbilities().forEach(pa->{
				System.out.println(pa);
			});
			
			f.getTriggeredAbilities().forEach(pa->{
				System.out.println(pa);
			});
			
			f.getActivatedAbilities().forEach(pa->{
				System.out.println(pa);
			});
			
			
	}
	
}