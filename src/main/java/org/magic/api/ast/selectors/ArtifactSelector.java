package org.magic.api.ast.selectors;

import org.magic.api.ast.interfaces.SelectorNode;

public record ArtifactSelector(boolean target) implements SelectorNode {
}