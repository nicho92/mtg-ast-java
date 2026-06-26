package org.magic.api.ast.factories;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.magic.api.ast.abilities.SpellAbility;
import org.magic.api.ast.abilities.StaticAbility;
import org.magic.api.ast.abilities.parsers.ActivatedAbilityParser;
import org.magic.api.ast.abilities.parsers.ContinuousModifierAbilityParser;
import org.magic.api.ast.abilities.parsers.KeywordAbilityParser;
import org.magic.api.ast.abilities.parsers.ModalAbilityParser;
import org.magic.api.ast.abilities.parsers.PlaneswalkerAbilityParser;
import org.magic.api.ast.abilities.parsers.ReplacementEffectParser;
import org.magic.api.ast.abilities.parsers.SagaAbilityParser;
import org.magic.api.ast.abilities.parsers.TriggeredAbilityParser;
import org.magic.api.ast.abilities.parsers.WordAbilityParser;
import org.magic.api.ast.effects.UnknownEffect;
import org.magic.api.ast.interfaces.AbilityNode;
import org.magic.api.ast.interfaces.parsers.AbilityParser;

public class AbilitiesFactory {

	private final List<AbilityParser> parsers;
	public static final AbilitiesFactory INSTANCE = new AbilitiesFactory();
	protected Logger logger = LogManager.getLogger(getClass());

	
	
	private AbilitiesFactory() {

		parsers = List.of(new ModalAbilityParser(), new PlaneswalkerAbilityParser(),new SagaAbilityParser(), new ReplacementEffectParser(),
				new KeywordAbilityParser(), new TriggeredAbilityParser(),new ActivatedAbilityParser(), 
				new ContinuousModifierAbilityParser(), new WordAbilityParser());
	}

	public List<AbilityNode> parse(String oracleText) {

		var ret = new ArrayList<AbilityNode>();

		if (oracleText == null || oracleText.isEmpty())
			return ret;

		for (var block : parseBlocks(oracleText)) {
			ret.add(parseLine(block));
		}
		return ret;
	}

	private List<String> parseBlocks(String oracleText) {

		var blocks = new ArrayList<String>();
		StringBuilder currentModalBlock = null;

		for (var rawLine : oracleText.lines().toList()) {
			var line = rawLine.trim();

			if (line.isBlank()) {
				continue;
			}

			if (isModalHeader(line)) {
				if (currentModalBlock != null) {
					blocks.add(currentModalBlock.toString());
				}
				currentModalBlock = new StringBuilder(line);
			} else if (isModeLine(line) && currentModalBlock != null) {
				currentModalBlock.append(System.lineSeparator()).append(line);
			} else {
				if (currentModalBlock != null) {
					blocks.add(currentModalBlock.toString());
					currentModalBlock = null;
				}
				blocks.add(line);
			}
		}

		if (currentModalBlock != null) {
			blocks.add(currentModalBlock.toString());
		}
		
		logger.debug("Parsing text {} as {} blocks",oracleText,blocks.size());
		
		return blocks;
	}

	private boolean isModalHeader(String line) {
		return line.matches("(?i)^Choose\\s+(one|two|one or both|up to one)\\s*[—-]?$");
	}

	private boolean isModeLine(String line) {
		return line.startsWith("•") || line.startsWith("-") || line.startsWith("—");
	}

	private AbilityNode parseLine(String line) {

		for (var parser : parsers) {

			if (parser.supports(line)) {
				return parser.parse(line);
			}
		}
		
		var spells = EffectFactory.INSTANCE.parse(line);
		if (spells instanceof UnknownEffect)
		{
			logger.warn("no abiliy parser found for \"{}\". Return Static One",line);
			return new StaticAbility(line);
		}
		else
		{
			return new SpellAbility(line, spells);
		}
		
	}
}
