package org.magic.api.ast.selectors;

public final class PermanentSelector implements TargetSelectorNode {

public static final PermanentSelector INSTANCE =
    new PermanentSelector();

private PermanentSelector() {
}
}