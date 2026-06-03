package org.magic.api.ast.engine;

import java.util.List;

import org.magic.api.ast.abilities.AbilityNode;
import org.magic.api.ast.abilities.StaticAbility;
import org.magic.api.ast.abilities.parsers.ActivatedAbilityParser;
import org.magic.api.ast.abilities.parsers.KeywordAbilityParser;
import org.magic.api.ast.abilities.parsers.TriggeredAbilityParser;
import org.magic.api.ast.interfaces.AbilityParser;

public class OracleParser {

    private final List<AbilityParser> parsers;

    public OracleParser() {

        parsers = List.of(
        		new KeywordAbilityParser(),
                new TriggeredAbilityParser(),
                new ActivatedAbilityParser()
        );
    }

    public CardAst parse( String cardName, String oracleText) {

        var ast = new CardAst(cardName);

        for (var line : oracleText.split("\\R")) {
            line = line.trim();
            if (line.isBlank()) {
                continue;
            }
            ast.addAbility(parseLine(line));
        }

        return ast;
    }

    private AbilityNode parseLine(String line) {

        for (var parser : parsers) {

            if (parser.supports(line)) {
                return parser.parse(line);
            }
        }

        return new StaticAbility(line);
    }
}