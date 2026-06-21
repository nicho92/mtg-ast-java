package org.magic.api.ast.interfaces;

import org.magic.api.ast.selectors.ArtifactSelector;
import org.magic.api.ast.selectors.CompoundSelector;
import org.magic.api.ast.selectors.CreatureSelector;
import org.magic.api.ast.selectors.LandSelector;
import org.magic.api.ast.selectors.PlayerSelector;
import org.magic.api.ast.selectors.SelfSelector;
import org.magic.api.ast.selectors.SubtypeSelector;
import org.magic.api.ast.selectors.TappedSelector;
import org.magic.api.ast.selectors.TextSelector;

public sealed interface SelectorNode 
	permits ArtifactSelector, CompoundSelector, CreatureSelector, LandSelector, PlayerSelector, SelfSelector, SubtypeSelector, TappedSelector, TextSelector{

}
