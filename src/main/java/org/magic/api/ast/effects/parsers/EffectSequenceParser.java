package org.magic.api.ast.effects.parsers;

import java.util.ArrayList;
import java.util.List;

import org.magic.api.ast.effects.factory.EffectFactory;
import org.magic.api.ast.interfaces.EffectNode;

public class EffectSequenceParser {

	public List<EffectNode> parse(String text) {
		return split(text).stream().map(EffectFactory.INSTANCE::parse).toList();
	}

	public List<String> split(String text) {

        var effects = new ArrayList<String>();
        var current = new StringBuilder();
        int braceDepth = 0;
        int parenthesisDepth = 0;
        boolean insideQuotes = false;
        int i=0;
        
        while (i < text.length()) {

        	char c = text.charAt(i);

            switch (c) {
	            case '{' -> braceDepth++;
	            case '}' -> braceDepth = Math.max(0, braceDepth - 1);
	            case '(' -> parenthesisDepth++;
	            case ')' -> parenthesisDepth = Math.max(0, parenthesisDepth - 1);
	            case '"' -> insideQuotes = !insideQuotes;
	            default -> {  
	            	//do nothing
	            }
            }

            if (isTopLevel(braceDepth, parenthesisDepth, insideQuotes)) {

                int separatorLength = separatorLength(text, i);

                if (separatorLength > 0) {
                    addEffect(effects, current);
                    current.setLength(0);
                    i += separatorLength;
                    continue;
                }

            }
            current.append(c);
            i++;
        }
        addEffect(effects, current);

        return effects;
    }

    private boolean isTopLevel(int braceDepth,int parenthesisDepth,boolean insideQuotes) {

        return braceDepth == 0
                && parenthesisDepth == 0
                && !insideQuotes;
    }

    private int separatorLength(String text, int index) {

        if (matchesSeparator(text, index, ", then ")) {
            return ", then ".length();
        }

        if (matchesSeparator(text, index, "; then ")) {
            return "; then ".length();
        }

        if (matchesDotThen(text, index)) {
            return dotThenLength(text, index);
        }

        return 0;
    }

    private boolean matchesSeparator(String text,int index,String separator) {

        return text.regionMatches(
                true,
                index,
                separator,
                0,
                separator.length());
    }

    private boolean matchesDotThen(String text,int index) {

        if (index >= text.length() || text.charAt(index) != '.') {
            return false;
        }

        int cursor = index + 1;

        while (cursor < text.length() && Character.isWhitespace(text.charAt(cursor))) {
            cursor++;
        }

        return text.regionMatches(
                true,
                cursor,
                "Then",
                0,
                4);
    }

    private int dotThenLength(String text,int index) {

        int cursor = index + 1;

        while (cursor < text.length() && Character.isWhitespace(text.charAt(cursor))) {
            cursor++;
        }

        return (cursor - index) + 4;
    }

    private void addEffect(List<String> effects,StringBuilder current) {
        	var effect =current.toString().trim();

        	if (!effect.isBlank()) {
        		effects.add(effect);
        }
    }
}
