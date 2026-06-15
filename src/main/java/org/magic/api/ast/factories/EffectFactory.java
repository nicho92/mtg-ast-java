package org.magic.api.ast.factories;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.magic.api.ast.effects.UnknownEffect;
import org.magic.api.ast.effects.parsers.AddManaEffectParser;
import org.magic.api.ast.effects.parsers.CreateTokenEffectParser;
import org.magic.api.ast.effects.parsers.DamageEffectParser;
import org.magic.api.ast.effects.parsers.DestroyEffectParser;
import org.magic.api.ast.effects.parsers.DiscardEffectParser;
import org.magic.api.ast.effects.parsers.DrawEffectParser;
import org.magic.api.ast.effects.parsers.ExileEffectParser;
import org.magic.api.ast.effects.parsers.GainLifeEffectParser;
import org.magic.api.ast.effects.parsers.GetEmblemEffectParser;
import org.magic.api.ast.effects.parsers.PutCountersEffectParser;
import org.magic.api.ast.effects.parsers.ReturnEffectParser;
import org.magic.api.ast.effects.parsers.SacrificeEffectParser;
import org.magic.api.ast.effects.parsers.TurnEffectParser;
import org.magic.api.ast.interfaces.EffectNode;
import org.magic.api.ast.interfaces.parsers.EffectParser;

public class EffectFactory {

	private final List<EffectParser> parsers;

	public static final EffectFactory INSTANCE = new EffectFactory();
	protected Logger logger = LogManager.getLogger(getClass());

	
	private EffectFactory() {

		parsers = List.of(new DrawEffectParser(), new GainLifeEffectParser(), new AddManaEffectParser(),
								new DamageEffectParser(), new DestroyEffectParser(), new GetEmblemEffectParser(),
								new CreateTokenEffectParser(), new PutCountersEffectParser(), new ExileEffectParser(),
								new ReturnEffectParser(), new DiscardEffectParser(), new SacrificeEffectParser(),
								new TurnEffectParser());
	}

	public  EffectNode parse(String text) {
		for (var parser : parsers) {

			if (parser.supports(text)) {
				return parser.parse(text);
			}
		}
		logger.warn("No EffectsParser found for \"{}\"",text);
		return new UnknownEffect(text);
	}
}