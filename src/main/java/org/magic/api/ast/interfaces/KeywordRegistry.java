package org.magic.api.ast.interfaces;

import java.util.Comparator;
import java.util.List;

public interface KeywordRegistry {

	
	public List<String> getKeywords();
	
    
    default List<String> orderedKeywords() {

    	return getKeywords()
	            .stream()
	            .sorted(
	                    Comparator.comparingInt(String::length).reversed()).toList();
	}
    
}
