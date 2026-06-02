package org.magic.api.ast.parser;

import org.magic.api.ast.abilities.AbilityNode;

public interface AbilityParser {

    boolean supports(String text);

    AbilityNode parse(String text);

}