package org.magic.api.ast.abilities;

import java.util.List;

import org.magic.api.ast.effects.EffectNode;

public record PlaneswalkerAbility(String loyalty, List<EffectNode> effects) implements AbilityNode {

}
