package org.magic.api.ast.interfaces;

import org.magic.api.ast.effects.AddManaEffect;
import org.magic.api.ast.effects.CreateTokenEffect;
import org.magic.api.ast.effects.DealDamageEffect;
import org.magic.api.ast.effects.DestroyTargetEffect;
import org.magic.api.ast.effects.DiscardCardsEffect;
import org.magic.api.ast.effects.DrawCardsEffect;
import org.magic.api.ast.effects.ExileEffect;
import org.magic.api.ast.effects.ExtraTurnEffect;
import org.magic.api.ast.effects.GainLifeEffect;
import org.magic.api.ast.effects.GetEmblemEffect;
import org.magic.api.ast.effects.MillEffect;
import org.magic.api.ast.effects.PutCountersEffect;
import org.magic.api.ast.effects.ReturnEffect;
import org.magic.api.ast.effects.SacrificeEffect;
import org.magic.api.ast.effects.UnknownEffect;

public sealed interface EffectNode permits 
AddManaEffect, CreateTokenEffect, DealDamageEffect, DestroyTargetEffect, 
DiscardCardsEffect, DrawCardsEffect, ExileEffect, ExtraTurnEffect, 
GainLifeEffect, GetEmblemEffect, MillEffect, PutCountersEffect, 
ReturnEffect, SacrificeEffect, UnknownEffect 
{
String text();
}