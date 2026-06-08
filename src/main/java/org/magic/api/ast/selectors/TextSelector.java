package org.magic.api.ast.selectors;

import org.magic.api.ast.interfaces.SelectorNode;

public record TextSelector(String oracleText) implements SelectorNode {
}