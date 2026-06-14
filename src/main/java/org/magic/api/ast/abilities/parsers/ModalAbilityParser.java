package org.magic.api.ast.abilities.parsers;

import java.util.Arrays;

import org.magic.api.ast.abilities.ModalAbility;
import org.magic.api.ast.abilities.model.ChoiceConstraint;
import org.magic.api.ast.abilities.model.ModeNode;
import org.magic.api.ast.interfaces.AbilityNode;
import org.magic.api.ast.interfaces.parsers.AbilityParser;
import org.magic.api.ast.interfaces.parsers.AbstractParser;
import org.magic.api.ast.util.EffectSequencerSplitter;

public class ModalAbilityParser extends AbstractParser<AbilityNode>  implements AbilityParser {

	@Override
	protected String getPattern() {
		return "^Choose\\s+(one|two|one or both|up to one)\\s*[—-]?$";
	}
	
	
	@Override
	public boolean supports(String text) {

		var firstLine = text.lines().findFirst().orElse("").trim();

		return super.supports(firstLine);
	}

	@Override
	public AbilityNode parse(String text) {

		var lines = text.lines().map(String::trim).filter(line -> !line.isBlank()).toList();

		var matcher = match(lines.getFirst());

		var choiceConstraint = parseChoiceConstraint(matcher.group(1));
	
		var modes = lines.stream().skip(1).map(this::stripBullet).map(modeText -> new ModeNode(modeText, EffectSequencerSplitter.INSTANCE.parse(modeText))).toList();

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
