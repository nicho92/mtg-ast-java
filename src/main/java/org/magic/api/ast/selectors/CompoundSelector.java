package org.magic.api.ast.selectors;

import java.util.List;

import org.magic.api.ast.interfaces.SelectorNode;

public record CompoundSelector(List<SelectorNode> selectors) implements SelectorNode {
}