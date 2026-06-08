package org.magic.api.ast.effects;

import org.magic.api.ast.interfaces.EffectNode;
import org.magic.api.ast.selectors.TargetSelectorNode;

public record ReturnEffect(TargetSelectorNode target, String destination) implements EffectNode {
}
