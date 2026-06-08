package org.magic.api.ast.engine;

public class MainTest {

	public static void main() {
		String oracleText = """

				Flying, first strike, vigilance, trample, haste.
				Protection from black and from red
				Ward {2}.

				  """;

		var ast = new OracleParser().toFacade("Example Card", oracleText);
		ast.getKeywordsAbilities().forEach(ka -> System.out.print(ka.name() + " (" + ka.parameter() + ") "));
	}
}
