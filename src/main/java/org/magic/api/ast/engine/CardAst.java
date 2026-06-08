package org.magic.api.ast.engine;

import java.util.ArrayList;
import java.util.List;

import org.magic.api.ast.abilities.AbilityNode;

public class CardAst {

	private final String name;

	private final List<AbilityNode> abilities = new ArrayList<>();

	public CardAst(String name) {
		this.name = name;
	}

	public void addAbility(AbilityNode ability) {
		abilities.add(ability);
	}

	public List<AbilityNode> getAbilities() {
		return abilities;
	}

	public String getName() {
		return name;
	}
}