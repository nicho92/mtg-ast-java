package org.magic.api.ast.selectors;

import org.magic.api.ast.interfaces.SelectorNode;

public record LandSelector(boolean target, boolean another, boolean controlledByYou) implements SelectorNode {
}