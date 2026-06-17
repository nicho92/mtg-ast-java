package org.magic.api.ast.effects;

import org.magic.api.ast.interfaces.EffectNode;

public record MillEffect(String text,int amount) implements EffectNode {}
