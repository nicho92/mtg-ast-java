package org.magic.api.ast.engine;

import org.magic.api.ast.util.CardQueryFacade;

public class MainTest {

	public static void main(String[] args) {
        String oracleText = """
            Flying, Ward {2}
            
            Whenever another creature dies, draw a card.
            
            {T}: Add {G}.
            """;
        
        CardAst ast = new OracleParser().parse("Example Card", oracleText);
        CardQueryFacade query = new CardQueryFacade(ast);
        
        // Utilisation simple et intuitive
        System.out.println("Carte: " + query.getCardName());
        System.out.println("Nombre d'abilities: " + query.getAbilityCount());
        System.out.println("Flying? " + query.hasKeyword("Flying"));
        System.out.println("Dies trigger? " + query.hasDiesTrigger());
        
        // Extraction détaillée
        query.getKeywordAbilities().forEach(k -> 
            System.out.println("Keyword: " + k.keyword())
        );
        
        query.getDrawEffects().forEach(d -> 
            System.out.println("Draw " + d.amount() + " cards")
        );
      
    }
}
