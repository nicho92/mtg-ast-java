package org.magic.api.ast.interfaces;

public interface ASTParser<T> {

	boolean supports(String text);
	
	T parse(String text);
	
}
