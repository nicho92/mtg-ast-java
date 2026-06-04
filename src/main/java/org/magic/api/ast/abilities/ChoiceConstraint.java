package org.magic.api.ast.abilities;

public record ChoiceConstraint(
        int minimum,
        int maximum,
        boolean sameModeAllowed
) {
}
