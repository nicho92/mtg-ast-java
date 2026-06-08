package org.magic.api.ast.abilities.parsers;

import java.util.Arrays;
import java.util.List;

import org.magic.api.ast.abilities.KeywordsAbility;
import org.magic.api.ast.abilities.model.Keyword;
import org.magic.api.ast.interfaces.AbilityNode;
import org.magic.api.ast.interfaces.KeywordRegistry;
import org.magic.api.ast.interfaces.parsers.AbilityParser;
import org.magic.api.ast.util.DefaultKeywordRegistry;

public class KeywordAbilityParser implements AbilityParser {

	private KeywordRegistry registry;

	public KeywordAbilityParser() {
			registry = new DefaultKeywordRegistry();

	}

	public void setRegistry(KeywordRegistry registry) {
		this.registry = registry;
	}

	@Override
	public boolean supports(String text) {

		String[] abilities = text.split(",");

		for (String ability : abilities) {

			String normalized = ability.trim();

			if (isKeyword(normalized)) {
				return true;
			}
		}

		return false;
	}

	private Keyword parseKeyword(String text) {

		return new Keyword(extractKeyword(text), extractParameter(text));
	}

	@Override
	public AbilityNode parse(String text) {

		List<Keyword> abilities = Arrays.stream(text.split(",")).map(String::trim).filter(this::isKeyword)
				.map(this::parseKeyword).toList();

		return new KeywordsAbility(abilities);
	}

	private boolean isKeyword(String text) {

		return registry.orderedKeywords().stream().anyMatch(keyword -> text.equalsIgnoreCase(keyword)
				|| text.toLowerCase().startsWith(keyword.toLowerCase() + " "));
	}

	private String extractKeyword(String text) {

		return registry.orderedKeywords().stream().filter(
				keyword -> text.equalsIgnoreCase(keyword) || text.toLowerCase().startsWith(keyword.toLowerCase() + " "))
				.findFirst().orElseThrow();
	}

	private String extractParameter(String text) {

		String keyword = extractKeyword(text);

		if (text.length() <= keyword.length()) {
			return null;
		}

		return text.substring(keyword.length()).trim();
	}
}