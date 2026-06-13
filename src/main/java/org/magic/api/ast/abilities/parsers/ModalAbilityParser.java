package org.magic.api.ast.abilities.parsers;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.magic.api.ast.abilities.ModalAbility;
import org.magic.api.ast.abilities.model.ChoiceConstraint;
import org.magic.api.ast.abilities.model.ModeNode;
import org.magic.api.ast.engine.EffectSequencerSplitter;
import org.magic.api.ast.interfaces.AbilityNode;
import org.magic.api.ast.interfaces.parsers.AbilityParser;

public class ModalAbilityParser implements AbilityParser {

	private static final Pattern HEADER_PATTERN = Pattern
			.compile("^Choose\\s+(one|two|one or both|up to one)\\s*[—-]?$", Pattern.CASE_INSENSITIVE);

	@Override
	public boolean supports(String text) {

		String firstLine = text.lines().findFirst().orElse("").trim();

		return HEADER_PATTERN.matcher(firstLine).matches();
	}

	@Override
	public AbilityNode parse(String text) {

		List<String> lines = text.lines().map(String::trim).filter(line -> !line.isBlank()).toList();

		var matcher = HEADER_PATTERN.matcher(lines.getFirst());
		matcher.find();

		var choiceConstraint = parseChoiceConstraint(matcher.group(1));
	
		List<ModeNode> modes = lines.stream().skip(1).map(this::stripBullet)
				.map(modeText -> new ModeNode(modeText, EffectSequencerSplitter.INSTANCE.parse(modeText))).toList();

		return new ModalAbility(text,choiceConstraint, modes);
	}

	private ChoiceConstraint parseChoiceConstraint(String text) {

		return switch (text.toLowerCase()) {
		case "one" -> new ChoiceConstraint(1, 1, false);
		case "two" -> new ChoiceConstraint(2, 2, false);
		case "one or both" -> new ChoiceConstraint(1, 2, false);
		case "up to one" -> new ChoiceConstraint(0, 1, false);
		default -> throw new IllegalArgumentException(text);
		};
	}

	private String stripBullet(String line) {

		return Arrays.stream(new String[] { "•", "-", "—" }).filter(line::startsWith).findFirst()
				.map(marker -> line.substring(marker.length()).trim()).orElse(line);
	}
}
