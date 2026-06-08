package org.magic.api.ast.selectors;

import org.magic.api.ast.interfaces.SelectorNode;

public record SubtypeSelector(String subtype) implements SelectorNode {
}
