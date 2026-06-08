package org.magic.api.ast.interfaces;

public interface EffectParser {

	boolean supports(String text);

	EffectNode parse(String text);

}