package org.magic.api.ast.selectors;

import org.magic.api.ast.interfaces.SelectorNode;

public record PlayerSelector(boolean target) implements SelectorNode {
}