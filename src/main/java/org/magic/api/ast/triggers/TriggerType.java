package org.magic.api.ast.triggers;

public enum TriggerType {

    ENTERS_BATTLEFIELD,
    LEAVES_BATTLEFIELD,
    DIES,

    ATTACKS,
    BLOCKS,
    BECOMES_BLOCKED,

    DEALS_DAMAGE,
    RECEIVES_DAMAGE,

    SPELL_CAST,
    SPELL_COUNTERED,

    DRAW_CARD,
    DISCARD_CARD,

    GAIN_LIFE,
    LOSE_LIFE,

    SACRIFICE,
    EXILE,
    DESTROY,

    BEGINNING_OF_UPKEEP,
    BEGINNING_OF_END_STEP,
    
    BECOMES_TARGET,

    COUNTER_ADDED,
    COUNTER_REMOVED,

    TOKEN_CREATED,

    KEYWORD_ACTION,

    SAGA_CHAPTER,
    CLASS_LEVEL,
    DUNGEON_COMPLETED, 
    
    UNKNOW
}