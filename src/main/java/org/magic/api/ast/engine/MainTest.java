package org.magic.api.ast.engine;

public class MainTest {

	public static void main() {
		String oracleText = """

				Flying, Ward {2}, Trample
				
				Sacrifice two artifacts : add {W}{G}.
				
				{T} : Add {W} or {G} 

				  """;

		var ast = OracleParser.toFacade("Example Card", oracleText);
		ast.getActivatedAbilities().forEach(ab->{
				System.out.println(ab.costs() + " -> " +  ab.effects());
		});
	}
}
