package org.magic.api.ast.effects.parsers;

import java.util.ArrayList;
import java.util.List;

import org.magic.api.ast.effects.EffectNode;
import org.magic.api.ast.factories.EffectFactory;

public class EffectSequenceParser {

     public List<EffectNode> parse(String text) {

        return split(text).stream()
                .map(EffectFactory.INSTANCE::parse)
                .toList();
    }

    private List<String> split(String text) {

        List<String> effects = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        int braceDepth = 0;
        int parenthesisDepth = 0;

        for (int i = 0; i < text.length(); i++) {

            char currentChar = text.charAt(i);

            switch (currentChar) {
                case '{' -> braceDepth++;
                case '}' -> braceDepth = Math.max(0, braceDepth - 1);
                case '(' -> parenthesisDepth++;
                case ')' -> parenthesisDepth = Math.max(0, parenthesisDepth - 1);
                default -> {
                    // No-op.
                }
            }

            if (braceDepth == 0 && parenthesisDepth == 0 && currentChar == '.') {
                addEffect(effects, current);
                current.setLength(0);
                continue;
            }

            if (braceDepth == 0 && parenthesisDepth == 0 && isThenSeparator(text, i)) {
                addEffect(effects, current);
                current.setLength(0);
                i += separatorLength(text, i) - 1;
                continue;
            }

            current.append(currentChar);
        }

        addEffect(effects, current);

        return effects;
    }

    private boolean isThenSeparator(String text, int index) {

        return matchesSeparator(text, index, ", then ")
                || matchesSeparator(text, index, "; then ");
    }

    private int separatorLength(String text, int index) {

        if (matchesSeparator(text, index, ", then ")) {
            return ", then ".length();
        }

        return "; then ".length();
    }

    private boolean matchesSeparator(String text, int index, String separator) {

        return text.regionMatches(true, index, separator, 0, separator.length());
    }

    private void addEffect(List<String> effects, StringBuilder current) {

        var effect = current.toString().trim();

        if (!effect.isBlank()) {
            effects.add(effect);
        }
    }
}
