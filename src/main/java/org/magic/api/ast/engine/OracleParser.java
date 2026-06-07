package org.magic.api.ast.engine;

import org.magic.api.ast.abilities.factory.AbilitiesFactory;
import org.magic.api.ast.util.CardQueryFacade;

public class OracleParser {


    public CardQueryFacade toFacade(String cardName, String oracleText) 
    {
    	 	return new CardQueryFacade(parse(cardName, oracleText));
    }
    
    
    public CardAst parse(String cardName, String oracleText) {

    	var ast = new CardAst(cardName);
    	
       new AbilitiesFactory().parse(oracleText).forEach(ast.getAbilities()::add);
       
       return ast;
    }

	
}
