package org.magic.api.ast.selectors;

public sealed interface TargetSelectorNode 
permits SelfSelector,
        PlayerSelector,
        CreatureSelector,
        ArtifactSelector,
        PermanentSelector,
        SubtypeSelector,
        CompoundSelector,
        TextSelector, TappedSelector {

	
}
