package org.magic.api.ast.engine;

import org.magic.api.ast.abilities.visitor.impl.AbilityDescriber;

public class MainTest {

	public static void main() {
        String oracleText = """
            Flying, Ward {2}
            
            Whenever another creature dies, draw a card.
            
            {T}: Add {G}.
            """;
        
        CardAst ast =new OracleParser().parse("Example Card", oracleText);
      
        ast.getAbilities().stream().map(ability -> ability.accept(new AbilityDescriber())).toList().forEach(System.out::println);
    
        
      
    }
}
