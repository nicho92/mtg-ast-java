package org.magic.api.ast.triggers;

import org.magic.api.ast.selectors.TargetSelectorNode;

public record DiesTrigger(TargetSelectorNode subject
) implements TriggerNode {
}