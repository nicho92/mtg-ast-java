package org.magic.api.ast.factories;

import org.magic.api.ast.selectors.ArtifactSelector;
import org.magic.api.ast.selectors.CreatureSelector;
import org.magic.api.ast.selectors.PlayerSelector;
import org.magic.api.ast.selectors.SelfSelector;
import org.magic.api.ast.selectors.TargetSelectorNode;
import org.magic.api.ast.selectors.TextSelector;

public class TargetSelectorFactory {
	
	
	public static final TargetSelectorFactory INSTANCE = new TargetSelectorFactory();

	public TargetSelectorNode parse(String text) {

	    String lower =
	            text.toLowerCase();

	    if (lower.equals("cardname")) {
	        return SelfSelector.INSTANCE;
	    }

	    if (lower.contains("creature")) {
	        return parseCreature(text);
	    }

	    if (lower.contains("artifact")) {
	        return parseArtifact(text);
	    }

	    if (lower.contains("player") || lower.contains("opponent") || lower.equals("you")) {
	        return parsePlayer(text);
	    }

	    return new TextSelector(text);
	}
	
	private TargetSelectorNode parsePlayer(
	        String text) {

	    return new PlayerSelector(
	            text.toLowerCase()
	                    .contains("target")
	    );
	}
	
	
	private TargetSelectorNode parseArtifact(
	        String text) {

	    return new ArtifactSelector(
	            text.toLowerCase()
	                    .contains("target")
	    );
	}
	
	
	private TargetSelectorNode parseCreature( String text) {

	    String lower =
	            text.toLowerCase();

	    return new CreatureSelector(

	            lower.contains("target"),

	            lower.contains("another"),

	            lower.contains("you control")
	    );
	}
	
	
	
}

