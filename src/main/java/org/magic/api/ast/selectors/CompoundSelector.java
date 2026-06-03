package org.magic.api.ast.selectors;

import java.util.List;

public record CompoundSelector(List<TargetSelectorNode> selectors) implements TargetSelectorNode {
}