package org.magic.api.ast.triggers;

public record UnknownTrigger(
        String oracleText
) implements TriggerNode {
}