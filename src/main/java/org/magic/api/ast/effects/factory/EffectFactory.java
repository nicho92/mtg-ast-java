package org.magic.api.ast.effects.factory;

import java.util.List;

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
import org.magic.api.ast.interfaces.EffectNode;
import org.magic.api.ast.interfaces.parsers.EffectParser;

public class EffectFactory {

	private final List<EffectParser> parsers;

	public static final EffectFactory INSTANCE = new EffectFactory();

	private EffectFactory() {

		parsers = List.of(new DrawEffectParser(), new GainLifeEffectParser(), new AddManaEffectParser(),
				new DamageEffectParser(), new DestroyEffectParser(), new GetEmblemEffectParser(),
				new CreateTokenEffectParser(), new PutCountersEffectParser(), new ExileEffectParser(),
				new ReturnEffectParser(), new DiscardEffectParser(), new SacrificeEffectParser());
	}

	public EffectNode parse(String text) {

		for (var parser : parsers) {

			if (parser.supports(text)) {
				return parser.parse(text);
			}
		}

		return new UnknownEffect(text);
	}
}