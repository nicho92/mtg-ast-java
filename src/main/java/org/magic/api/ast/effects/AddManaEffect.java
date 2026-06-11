package org.magic.api.ast.effects;

import java.util.List;

import org.magic.api.ast.interfaces.CostNode;
import org.magic.api.ast.interfaces.EffectNode;

public record AddManaEffect(List<CostNode>  mana) implements EffectNode {
	
}