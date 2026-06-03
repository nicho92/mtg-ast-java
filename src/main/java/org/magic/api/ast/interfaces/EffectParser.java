package org.magic.api.ast.interfaces;

import org.magic.api.ast.effects.EffectNode;

public interface EffectParser {

    boolean supports(String text);

    EffectNode parse(String text);

}