package org.magic.api.ast.interfaces.parsers;

import org.magic.api.ast.interfaces.EffectNode;

public interface EffectParser {

	boolean supports(String text);

	EffectNode parse(String text);

}