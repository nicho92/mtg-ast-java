package org.magic.api.ast.engine;

import org.magic.api.ast.abilities.factory.AbilitiesFactory;
import org.magic.api.ast.util.ASTFacade;

public class OracleParser {

	public ASTFacade toFacade(String cardName, String oracleText) {
		return new ASTFacade(parse(cardName, oracleText));
	}

	public CardAst parse(String cardName, String oracleText) {

		var ast = new CardAst(cardName);

		AbilitiesFactory.INSTANCE.parse(oracleText).forEach(ast.getAbilities()::add);

		return ast;
	}

}
