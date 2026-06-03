package org.magic.api.ast.main;

import org.magic.api.ast.engine.OracleParser;

public class Test {

	public static void main(String[] args) {
		var oracle=	"""
			Flying, Ward {2}, Trample

			Whenever another creature dies, draw a card.
			
			Sacrifice three artifacts : add {W}.
			
			""";
		var test = new OracleParser().parse("Test", oracle);
		
		
		
		
		test.getAbilities().forEach(e->{
			
			System.out.println(e.toString());
			
		});
	}

}
