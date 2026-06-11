package org.magic.api.ast.abilities;

import java.util.List;

import org.magic.api.ast.abilities.model.Keyword;
import org.magic.api.ast.interfaces.AbilityNode;
import org.magic.api.ast.interfaces.visitors.AbilityVisitor;

public record KeywordsAbility(String text,List<Keyword> keywords) implements AbilityNode {

	@Override
	public <T> T accept(AbilityVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
