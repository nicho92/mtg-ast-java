package org.magic.api.ast.parser.interfaces;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class KeywordRegistry {

	private Set<String> getKeywords() {
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
    
    public List<String> orderedKeywords() {

    	return getKeywords()
	            .stream()
	            .sorted(
	                    Comparator.comparingInt(String::length).reversed()).toList();
	}
    
}