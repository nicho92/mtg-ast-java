package org.magic.api.ast.engine;

public class MainTest {

	public static void main() {
		
			String oracleText = """
		            Flying, Ward {2}

		            Whenever another creature dies, draw a card.

		            {T}: Add {G}.
		            """;

		        var ast = new OracleParser().toFacade("Example Card", oracleText);
		        
		        ast.getAllAbilities().forEach(a->{
		        	System.out.println(a);
		        });
	}
	
}
