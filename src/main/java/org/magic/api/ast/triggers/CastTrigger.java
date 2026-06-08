package org.magic.api.ast.triggers;

import org.magic.api.ast.selectors.TargetSelectorNode;

public record CastTrigger(TargetSelectorNode subject) implements TriggerNode {
}