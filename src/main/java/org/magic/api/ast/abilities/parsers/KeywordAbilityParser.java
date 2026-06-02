package org.magic.api.ast.abilities.parsers;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.magic.api.ast.abilities.AbilityNode;
import org.magic.api.ast.abilities.KeywordAbility;
import org.magic.api.ast.abilities.KeywordGroupAbility;
import org.magic.api.ast.parser.interfaces.AbilityParser;
import org.magic.api.ast.parser.interfaces.KeywordRegistry;

public class KeywordAbilityParser implements AbilityParser {

	private final KeywordRegistry registry;

	public KeywordAbilityParser() {

		this.registry = new KeywordRegistry() {
			
			@Override
			public Set<String> getKeywords() {
		    	return Set.of(
		    	        "Flying",
		    	        "Trample",
		    	        "First strike",
		    	        "Double strike",
		    	        "Deathtouch",
		    	        "Hexproof",
		    	        "Ward",
		    	        "Cycling",
		    	        "Equip"
		    	);
			}
		};
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

	private KeywordAbility parseKeyword(
	        String text) {

	    return new KeywordAbility(
	            extractKeyword(text),
	            extractParameter(text)
	    );
	}
	
	@Override
	public AbilityNode parse(String text) {

	    List<KeywordAbility> abilities =
	            Arrays.stream(text.split(","))
	                    .map(String::trim)
	                    .filter(this::isKeyword)
	                    .map(this::parseKeyword)
	                    .toList();

	    return new KeywordGroupAbility(
	            abilities
	    );
	}
	private boolean isKeyword(String text) {

		return registry.getKeywords().stream().anyMatch(keyword -> text.equalsIgnoreCase(keyword)
				|| text.toLowerCase().startsWith(keyword.toLowerCase() + " "));
	}

	private String extractKeyword(String text) {

		return registry.getKeywords().stream().filter(
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