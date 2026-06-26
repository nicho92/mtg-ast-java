package org.magic.api.ast.test;

import org.junit.jupiter.api.Test;
import org.magic.api.ast.engine.OracleParser;

public class Tester {

	@Test
	void test() {
		var s = """
				[+1]: Scry 2, then draw a card.
				[-2]: Target creature gets -2/-2 until end of turn. If it dies this turn, return it to the battlefield under your control at the beginning of the next end step.
				[-7]: You get an emblem with "Whenever a creature dies, put a +1/+1 counter on target creature you control and draw a card."
				""";
		
		
		var ast = OracleParser.toFacade("test", s);
		
		
		ast.getAllAbilities().forEach(pa->{
			
			System.out.println(pa);
			
			
		});
		
		
		
	}
	
	
}
