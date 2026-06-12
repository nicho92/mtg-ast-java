package org.magic.api.ast.interfaces.parsers;

public interface ASTParser<T> {

	boolean supports(String text);
	
	T parse(String text);
	
}
