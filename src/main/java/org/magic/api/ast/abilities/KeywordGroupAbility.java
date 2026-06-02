package org.magic.api.ast.abilities;

import java.util.List;

public record KeywordGroupAbility(List<KeywordAbility> keywords) implements AbilityNode {
}