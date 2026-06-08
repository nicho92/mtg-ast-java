package org.magic.api.ast.interfaces.parsers;

import org.magic.api.ast.interfaces.AbilityNode;

public interface AbilityParser {

	boolean supports(String text);

	AbilityNode parse(String text);

}