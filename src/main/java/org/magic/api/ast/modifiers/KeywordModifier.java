package org.magic.api.ast.modifiers;

import org.magic.api.ast.abilities.model.Keyword;
import org.magic.api.ast.costs.model.DurationType;
import org.magic.api.ast.interfaces.ModifierNode;

public record KeywordModifier(Keyword keyword, DurationType duration) implements ModifierNode {
}
