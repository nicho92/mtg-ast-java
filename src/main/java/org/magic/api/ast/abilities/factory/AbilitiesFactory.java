package org.magic.api.ast.abilities.factory;

import java.util.ArrayList;
import java.util.List;

import org.magic.api.ast.abilities.AbilityNode;
import org.magic.api.ast.abilities.StaticAbility;
import org.magic.api.ast.abilities.parsers.AbilityParser;
import org.magic.api.ast.abilities.parsers.ActivatedAbilityParser;
import org.magic.api.ast.abilities.parsers.ContinuousModifierAbilityParser;
import org.magic.api.ast.abilities.parsers.KeywordAbilityParser;
import org.magic.api.ast.abilities.parsers.ModalAbilityParser;
import org.magic.api.ast.abilities.parsers.PlaneswalkerAbilityParser;
import org.magic.api.ast.abilities.parsers.ReplacementEffectParser;
import org.magic.api.ast.abilities.parsers.SagaAbilityParser;
import org.magic.api.ast.abilities.parsers.TriggeredAbilityParser;
import org.magic.api.ast.abilities.parsers.WordAbilityParser;

public class AbilitiesFactory {

	private final List<AbilityParser> parsers;
	public static final AbilitiesFactory INSTANCE = new AbilitiesFactory();

	private AbilitiesFactory() {

		parsers = List.of(new ModalAbilityParser(), new ReplacementEffectParser(),
				new ContinuousModifierAbilityParser(), new KeywordAbilityParser(), new TriggeredAbilityParser(),
				new PlaneswalkerAbilityParser(), new ActivatedAbilityParser(), new SagaAbilityParser(),
				new WordAbilityParser());
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

		List<String> blocks = new ArrayList<>();
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

		return new StaticAbility(line);
	}
}
