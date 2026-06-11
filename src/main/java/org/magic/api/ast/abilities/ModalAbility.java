package org.magic.api.ast.abilities;

import java.util.List;

import org.magic.api.ast.abilities.model.ChoiceConstraint;
import org.magic.api.ast.abilities.model.ModeNode;
import org.magic.api.ast.interfaces.AbilityNode;
import org.magic.api.ast.interfaces.visitors.AbilityVisitor;

public record ModalAbility(String text,ChoiceConstraint choiceConstraint, List<ModeNode> modes) implements AbilityNode {

	@Override
	public <T> T accept(AbilityVisitor<T> visitor) {
		return visitor.visit(this);
	}
}