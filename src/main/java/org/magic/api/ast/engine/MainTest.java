package org.magic.api.ast.engine;

public class MainTest {

	public static void main() {
        String oracleText = """
You may activate the loyalty abilities of Urza twice each turn rather than only once.
+2: Artifact, instant, and sorcery spells you cast this turn cost {2} less to cast. You gain 2 life.
−10: You get an emblem with "At the beginning of your end step, create three 1/1 white Cat creature tokens with lifelink.".
            """;
        
        var ast =new OracleParser().parse("Example Card", oracleText);
      
        ast.getAbilities().stream().toList().forEach(System.out::println);
    
        
      
    }
}
