package org.magic.api.ast.triggers;

public sealed interface TriggerNode
permits EntersBattlefieldTrigger,
        DiesTrigger,
        UpkeepTrigger,
        UnknownTrigger {
}