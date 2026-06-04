package org.magic.api.ast.abilities;

import java.util.List;

public record ModalAbility(
        ChoiceConstraint choiceConstraint,
        List<ModeNode> modes
) implements AbilityNode {
}
