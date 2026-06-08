package org.magic.api.ast.abilities.model;

public record ChoiceConstraint(int minimum, int maximum, boolean sameModeAllowed) {
}
