package org.magic.api.ast.selectors;

public final class SelfSelector implements TargetSelectorNode {

public static final SelfSelector INSTANCE = new SelfSelector();

	private SelfSelector() {
	}
}