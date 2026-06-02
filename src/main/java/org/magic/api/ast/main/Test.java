package org.magic.api.ast.main;

import org.magic.api.ast.engine.OracleParser;

public class Test {

	public static void main(String[] args) {
		var oracle=	"""
			Flying
			
			Whenever another creature dies, draw a card.
			
			{T}: Add {G}.""";
		
		
		var test = new OracleParser().parse("Test", oracle);
		
		
		System.out.println(test.getAbilities());
	}

}
