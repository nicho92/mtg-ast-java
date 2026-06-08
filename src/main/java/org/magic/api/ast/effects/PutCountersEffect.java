package org.magic.api.ast.effects;

import org.magic.api.ast.interfaces.EffectNode;
import org.magic.api.ast.selectors.TargetSelectorNode;

public record PutCountersEffect(int amount, String counterType, TargetSelectorNode target) implements EffectNode {
}
