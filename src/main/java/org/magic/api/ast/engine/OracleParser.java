package org.magic.api.ast.engine;

import java.util.List;

import org.magic.api.ast.abilities.AbilityNode;
import org.magic.api.ast.abilities.StaticAbility;
import org.magic.api.ast.parser.ActivatedAbilityParser;
import org.magic.api.ast.parser.TriggeredAbilityParser;
import org.magic.api.ast.parser.interfaces.AbilityParser;

public class OracleParser {

    private final List<AbilityParser> parsers;

    public OracleParser() {

        parsers = List.of(
             //   new KeywordAbilityParser(),
                new TriggeredAbilityParser(),
                new ActivatedAbilityParser()
        );
    }

    public CardAst parse(
            String cardName,
            String oracleText) {

        CardAst card = new CardAst(cardName);

        String[] lines =
                oracleText.split("\\R");

        for (String line : lines) {

            line = line.trim();

            if (line.isBlank()) {
                continue;
            }

            AbilityNode ability = parseLine(line);

            card.addAbility(ability);
        }

        return card;
    }

    private AbilityNode parseLine(String line) {

        for (AbilityParser parser : parsers) {

            if (parser.supports(line)) {
                return parser.parse(line);
            }
        }

        return new StaticAbility(line);
    }
}