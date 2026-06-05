package org.magic.api.ast.engine;

import java.util.ArrayList;
import java.util.List;

import org.magic.api.ast.abilities.AbilityNode;
import org.magic.api.ast.abilities.StaticAbility;
import org.magic.api.ast.abilities.parsers.ActivatedAbilityParser;
import org.magic.api.ast.abilities.parsers.ContinuousModifierAbilityParser;
import org.magic.api.ast.abilities.parsers.KeywordAbilityParser;
import org.magic.api.ast.abilities.parsers.ModalAbilityParser;
import org.magic.api.ast.abilities.parsers.ReplacementEffectParser;
import org.magic.api.ast.abilities.parsers.SagaAbilityParser;
import org.magic.api.ast.abilities.parsers.PlaneswalkerAbilityParser;
import org.magic.api.ast.abilities.parsers.TriggeredAbilityParser;
import org.magic.api.ast.interfaces.AbilityParser;

public class OracleParser {

    private final List<AbilityParser> parsers;

    public OracleParser() {

        parsers = List.of(
        		new ModalAbilityParser(),
        		new ReplacementEffectParser(),
        		new ContinuousModifierAbilityParser(),
        		new KeywordAbilityParser(),
                new TriggeredAbilityParser(),
                new PlaneswalkerAbilityParser(),
                new ActivatedAbilityParser(),
                new SagaAbilityParser()
        );
    }

    public CardAst parse(String cardName, String oracleText) {

        var ast = new CardAst(cardName);

        for (var block : parseBlocks(oracleText)) {
            ast.addAbility(parseLine(block));
        }

        return ast;
    }

    private List<String> parseBlocks(String oracleText) {

        List<String> blocks = new ArrayList<>();
        String[] lines = oracleText.split("\\R");

        for (int index = 0; index < lines.length; index++) {

            String line = lines[index].trim();

            if (line.isBlank()) {
                continue;
            }

            if (isModalHeader(line)) {
                StringBuilder modalBlock = new StringBuilder(line);

                while (index + 1 < lines.length) {
                    String nextLine = lines[index + 1].trim();

                    if (nextLine.isBlank()) {
                        index++;
                        continue;
                    }

                    if (!isModeLine(nextLine)) {
                        break;
                    }

                    modalBlock.append(System.lineSeparator()).append(nextLine);
                    index++;
                }

                blocks.add(modalBlock.toString());
                continue;
            }

            blocks.add(line);
        }

        return blocks;
    }

    private boolean isModalHeader(String line) {
        return line.matches("(?i)^Choose\\s+(one|two|one or both|up to one)\\s*[—-]?$");
    }

    private boolean isModeLine(String line) {
        return line.startsWith("•") || line.startsWith("-") || line.startsWith("—");
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
