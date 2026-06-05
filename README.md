# MTG AST Java

[![Java](https://img.shields.io/badge/Java-25%2B-orange.svg)](https://www.oracle.com/java/)
[![Maven](https://img.shields.io/badge/build-Maven-blue.svg)](https://maven.apache.org/)
[![License](https://img.shields.io/badge/license-Apache%202.0-green.svg)](http://www.apache.org/licenses/LICENSE-2.0)

**MTG AST Java** is a lightweight Java library for parsing _Magic: The Gathering_ oracle text into a structured abstract syntax tree (AST). It turns card text into typed Java records for abilities, triggers, costs, and effects so downstream applications can inspect and reason about card behavior programmatically.

## Features

- Parse multi-line oracle text with `OracleParser`.
- Represent parsed cards with `CardAst`.
- Detect common ability forms:
  - Keyword abilities, including grouped keywords.
  - Triggered abilities.
  - Activated abilities with costs and effects.
  - Static abilities as a fallback for unsupported text.
  - Modal abilities with `Choose one —` style mode bullets.
  - Replacement effects with `If ... would ..., ... instead.` text.
  - Basic continuous power/toughness modifiers.
- Model common game concepts with sealed interfaces and records:
  - Costs: mana costs, tap costs, sacrifice costs.
  - Effects: draw cards, add mana, deal damage, gain life, destroy targets, create tokens, counters, exile, return, discard, sacrifice, and unknown effects.
  - Triggers: enters-the-battlefield, dies, upkeep, and unknown triggers.
- Built with Maven as a reusable JAR.

## Requirements

- Java 25 or newer, as configured by `maven.compiler.release`.
- Apache Maven 3.9+.

## Installation

Clone the repository and build the project locally:

```bash
git clone https://github.com/nicho92/mtg-ast-java.git
cd mtg-ast-java
mvn clean package
```

To install the artifact into your local Maven repository:

```bash
mvn clean install
```

Then add it to another Maven project:

```xml
<dependency>
  <groupId>com.github.nicho92</groupId>
  <artifactId>mtg-ast-java</artifactId>
  <version>0.0.2</version>
</dependency>
```

## Quick start

```java
import org.magic.api.ast.engine.CardAst;
import org.magic.api.ast.engine.OracleParser;

public class Example {
    public static void main(String[] args) {
        String oracleText = """
            Flying, Ward {2}

            Whenever another creature dies, draw a card.

            {T}: Add {G}.
            """;

        CardAst ast = new OracleParser().parse("Example Card", oracleText);

        System.out.println(ast.getName());
        System.out.println(ast.getAbilities());
    }
}
```

Example output:

```text
Example Card
[KeywordGroupAbility[keywords=[KeywordAbility[keyword=Flying, parameter=null], KeywordAbility[keyword=Ward, parameter={2}]]], TriggeredAbility[trigger=DiesTrigger[subject=another creature], effects=[DrawCardsEffect[amount=1]]], ActivatedAbility[costs=[Tap], effects=[AddManaEffect[mana={G}]]]]
```

## Supported AST model

### Card

| Type | Description |
| --- | --- |
| `CardAst` | Root object containing the card name and parsed ability list. |

### Abilities

| Type | Description |
| --- | --- |
| `KeywordAbility` | A single keyword ability, optionally with a parameter such as `Ward {2}`. |
| `KeywordGroupAbility` | A comma-separated group of keyword abilities. |
| `TriggeredAbility` | A trigger paired with one or more effects. |
| `ActivatedAbility` | One or more costs paired with one or more effects. |
| `StaticAbility` | Fallback node preserving oracle text that does not match another parser. |
| `ModalAbility` | A choice constraint paired with bullet modes and their parsed effects. |
| `ReplacementEffectAbility` | A replacement event paired with replacement effects. |
| `ContinuousModifierAbility` | A basic static modifier such as `Creatures you control get +1/+1.` |

### Costs

| Type | Example |
| --- | --- |
| `ManaCost` | `{2}{G}` |
| `TapCost` | `{T}` |
| `SacrificeCost` | `Sacrifice a creature` |

### Effects

| Type | Example |
| --- | --- |
| `AddManaEffect` | `Add {G}.` |
| `CreateTokenEffect` | `Create a 1/1 white Soldier creature token.` |
| `DealDamageEffect` | `Deal 3 damage to any target.` |
| `DestroyTargetEffect` | `Destroy target creature.` |
| `DiscardCardsEffect` | `Each opponent discards a card.` |
| `DrawCardsEffect` | `Draw a card.` |
| `ExileEffect` | `Exile target creature.` |
| `GainLifeEffect` | `You gain 3 life.` |
| `PutCountersEffect` | `Put two +1/+1 counters on target creature.` |
| `ReturnEffect` | `Return target creature card from your graveyard to the battlefield.` |
| `SacrificeEffect` | `Each opponent sacrifices a creature.` |
| `UnknownEffect` | Fallback for unsupported effect text. |

### Triggers

| Type | Example |
| --- | --- |
| `EntersBattlefieldTrigger` | `When this creature enters the battlefield, ...` |
| `DiesTrigger` | `Whenever another creature dies, ...` |
| `UpkeepTrigger` | `At the beginning of your upkeep, ...` |
| `UnknownTrigger` | Fallback for unsupported trigger text. |

## Project structure

```text
src/main/java/org/magic/api/ast/
├── abilities/      # Ability node types and ability parsers
├── costs/          # Cost node types and cost parsers
├── effects/        # Effect node types and effect parsers
├── engine/         # OracleParser and CardAst entry points
├── factories/      # Parser factory helpers
├── parser/         # Parser interfaces and keyword registry
└── triggers/       # Trigger node types
```

## Development

Build the project:

```bash
mvn clean package
```

Run the sample entry point:

```bash
mvn exec:java -Dexec.mainClass=org.magic.api.ast.main.Test
```

> Note: the current `pom.xml` configures the compiler for Java 25. If your local JDK is older, Maven compilation will fail until you install a compatible JDK or adjust the compiler release for your environment.

## Roadmap ideas

- Add unit tests for each parser and AST node type.
- Expand parser coverage for replacement effects, modal choices, counters, combat restrictions, and target clauses.
- Publish releases to a package repository.
- Add generated API documentation.

## Contributing

Contributions are welcome. Please keep parser additions small and focused, and include examples or tests that show the oracle text being parsed.

1. Fork the repository.
2. Create a feature branch.
3. Make your changes.
4. Run the Maven build.
5. Open a pull request.

## License

This project is licensed under the Apache License, Version 2.0. See the project metadata in `pom.xml` for license details.

## Disclaimer

This project is an unofficial fan-made tool and is not affiliated with, endorsed by, sponsored by, or specifically approved by Wizards of the Coast LLC. _Magic: The Gathering_ and related names are trademarks of Wizards of the Coast LLC.
