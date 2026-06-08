package org.magic.api.ast.selectors.factory;

import org.magic.api.ast.interfaces.SelectorNode;
import org.magic.api.ast.selectors.ArtifactSelector;
import org.magic.api.ast.selectors.CreatureSelector;
import org.magic.api.ast.selectors.LandSelector;
import org.magic.api.ast.selectors.PlayerSelector;
import org.magic.api.ast.selectors.SelfSelector;
import org.magic.api.ast.selectors.TextSelector;

public class SelectorFactory {

	public static final SelectorFactory INSTANCE = new SelectorFactory();

	private SelectorFactory() {

	}

	public SelectorNode parse(String text) {

		String lower = text.toLowerCase();

		if (lower.contains("cardname")) {
			return new SelfSelector();
		}

		if (lower.contains("creature")) {
			return parseCreature(text);
		}

		if (lower.contains("artifact")) {
			return parseArtifact(text);
		}

		if (lower.contains("player") || lower.contains("opponent") || lower.contains("you")) {
			return parsePlayer(text);
		}

		if (lower.contains("land")) {
			return parseLand(text);
		}

		return new TextSelector(text);
	}

	private SelectorNode parsePlayer(String text) {

		return new PlayerSelector(text.toLowerCase().contains("target"));
	}

	private SelectorNode parseLand(String text) {

		String lower = text.toLowerCase();

		return new LandSelector(

				lower.contains("target"),

				lower.contains("another"),

				lower.contains("you control") || lower.contains("under your control"));
	}

	private SelectorNode parseArtifact(String text) {

		return new ArtifactSelector(text.toLowerCase().contains("target"));
	}

	private SelectorNode parseCreature(String text) {

		String lower = text.toLowerCase();

		return new CreatureSelector(

				lower.contains("target"),

				lower.contains("another"),

				lower.contains("you control"));
	}

}
