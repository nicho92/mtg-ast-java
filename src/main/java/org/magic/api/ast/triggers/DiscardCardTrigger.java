package org.magic.api.ast.triggers;

import org.magic.api.ast.selectors.TargetSelectorNode;

public record DiscardCardTrigger(TargetSelectorNode player) implements TriggerNode {
}
