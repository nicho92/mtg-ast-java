package org.magic.api.ast.selectors;

import org.magic.api.ast.interfaces.SelectorNode;

public record PlayerSelector(boolean target,boolean you) implements SelectorNode {
}