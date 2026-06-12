# MTG AST Java

[![Java](https://img.shields.io/badge/Java-25%2B-orange.svg)](https://www.oracle.com/java/)
[![Maven](https://img.shields.io/badge/build-Maven-blue.svg)](https://maven.apache.org/)
[![License](https://img.shields.io/badge/license-Apache%202.0-green.svg)](http://www.apache.org/licenses/LICENSE-2.0)
[![GitHub](https://img.shields.io/badge/GitHub-nicho92%2Fmtg--ast--java-blue)](https://github.com/nicho92/mtg-ast-java)

**MTG AST Java** is a lightweight, powerful Java library for parsing _Magic: The Gathering_ oracle text into a structured abstract syntax tree (AST). It transforms complex card abilities into typed Java records, enabling programmatic analysis and manipulation of MTG card mechanics.

## 🎯 Features

- **Comprehensive Parsing**: Parse multi-line oracle text with `OracleParser` and convert it to a fully-typed AST.
- **Ability Detection**: Recognize and parse all major ability types:
  - Keyword abilities (including grouped keywords)
  - Triggered abilities
  - Activated abilities with costs and effects
  - Static abilities (fallback for unsupported text)
  - Modal abilities (`Choose one —` style)
  - Replacement effects (`If ... would ..., ... instead.`)
  - Continuous power/toughness modifiers
- **Structured Game Concepts**: Model using sealed interfaces and records:
  - **Costs**: Mana costs, tap costs, sacrifice costs
  - **Effects**: Draw cards, add mana, deal damage, gain life, destroy targets, create tokens, add counters, exile, return, discard, sacrifice, and more
  - **Triggers**: Enters-the-battlefield, dies, upkeep, and extensible unknown triggers
- **Production-Ready**: Built with Maven as a reusable JAR for easy integration.

## 📋 Requirements

- **Java 25** or newer (configured by `maven.compiler.release`)
- **Apache Maven 3.9+**

## 📦 Installation

### Clone and Build Locally

```bash
git clone https://github.com/nicho92/mtg-ast-java.git
cd mtg-ast-java
mvn clean package
```

### Install to Local Maven Repository

```bash
mvn clean install
```

### Add to Your Maven Project

```xml
<dependency>
  <groupId>com.github.nicho92</groupId>
  <artifactId>mtg-ast-java</artifactId>
  <version>0.0.14</version>
</dependency>
```

## 🚀 Quick Start

```java
public class MainTest {

	public static void main() {
		String oracleText = """

				Flying, Ward {2}, Trample
				
				Sacrifice two artifacts : add {W}{G}.
				
				{T} : Add {W} or {G} 

				  """;

		var ast = OracleParser.toFacade("Example Card", oracleText);
		ast.getAllAbilities().forEach(ab->{
				System.out.println(ab);
		});
	}
}

```

**Example Output:**
```
Example Card
KeywordsAbility[keywords=[Keyword[name=Flying, parameter=null], Keyword[name=Ward, parameter={2}], Keyword[name=Trample, parameter=null]]]
ActivatedAbility[costs=[SacrificeCost[target=Artifact, qty=2]], effects=[AddManaEffect[mana={W}{G}]]]
ActivatedAbility[costs=[TAP], effects=[AddManaEffect[mana={W} or {G}]]]

```

## 📚 Supported AST Model

### Card Structure

| Type | Description |
| --- | --- |
| `CardAst` | Root object containing the card name and fully-parsed ability list. |

### Ability Types

| Type | Description | Example |
| --- | --- | --- |
| `KeywordAbility` | A single keyword ability, optionally with a parameter. | `Flying`, `Ward {2}` |
| `KeywordGroupAbility` | A comma-separated group of keyword abilities. | `Flying, Haste, Vigilance` |
| `TriggeredAbility` | A trigger paired with one or more effects. | `When this enters, draw a card.` |
| `ActivatedAbility` | One or more costs paired with one or more effects. | `{T}: Add {G}.` |
| `StaticAbility` | Fallback node for oracle text that doesn't match other parsers. | Custom text |
| `ModalAbility` | A choice constraint with bullet modes and parsed effects. | `Choose one — Draw a card. or Discard a card.` |
| `ReplacementEffectAbility` | A replacement event paired with replacement effects. | `If a creature would die, ...` |
| `ContinuousModifierAbility` | Static modifiers like power/toughness changes. | `Creatures you control get +1/+1.` |

### Cost Types

| Type | Example |
| --- | --- |
| `ManaCost` | `{2}{G}` |
| `TapCost` | `{T}` |
| `SacrificeCost` | `Sacrifice a creature` |

### Effect Types

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

### Trigger Types

| Type | Example |
| --- | --- |
| `EntersBattlefieldTrigger` | `When this creature enters the battlefield, ...` |
| `DiesTrigger` | `Whenever another creature dies, ...` |
| `UpkeepTrigger` | `At the beginning of your upkeep, ...` |
| `UnknownTrigger` | Fallback for unsupported trigger text. |

## 📁 Project Structure

```
src/main/java/org/magic/api/ast/
├── abilities/      # Ability node types and ability parsers
├── costs/          # Cost node types and cost parsers
├── effects/        # Effect node types and effect parsers
├── engine/         # OracleParser and CardAst entry points
├── factories/      # Parser factory helpers
├── parser/         # Parser interfaces and keyword registry
└── triggers/       # Trigger node types and trigger parsers
```

## 🔧 Development

### Build the Project

```bash
mvn clean package
```

### Run Tests and Sample

```bash
mvn exec:java -Dexec.mainClass=org.magic.api.ast.main.Test
```

> **Note**: The `pom.xml` is configured for Java 25. If your JDK is older, either install a compatible JDK or adjust the `maven.compiler.release` property in `pom.xml`.

## 📅 Roadmap

- [ ] Add comprehensive unit tests for all parsers and AST node types
- [ ] Expand parser coverage for:
  - Replacement effects with complex conditions
  - Modal choices with nested parsing
  - Counter mechanics and interactions
  - Combat restrictions (e.g., `can't block`)
  - Advanced target clauses
- [x] Publish releases to Maven Central or GitHub Packages
- [ ] Generate and host API documentation
- [ ] Add support for older MTG card text variations
- [ ] Performance optimizations for batch parsing

## 🤝 Contributing

Contributions are welcome! Please follow these guidelines:

1. **Fork** the repository
2. **Create** a feature branch (`git checkout -b feature/amazing-feature`)
3. **Make** your changes with focused commits
4. **Include** examples or unit tests demonstrating your changes
5. **Run** `mvn clean package` to verify the build
6. **Push** to your branch and **open** a pull request

### Contribution Tips

- Keep parser additions small and focused
- Test your parser with multiple card examples
- Update relevant sections in the README
- Follow the existing code style and structure

## 📄 License

This project is licensed under the **Apache License, Version 2.0**.  
See the `pom.xml` file for complete license details.

For more information, visit: [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0)

## ⚖️ Disclaimer

This project is an **unofficial fan-made tool** and is not affiliated with, endorsed by, sponsored by, or specifically approved by Wizards of the Coast LLC.

_Magic: The Gathering_ is a registered trademark of Wizards of the Coast LLC.  
All MTG card data and mechanics are property of Wizards of the Coast LLC.

This library is provided for educational and fan purposes only. Use responsibly and in accordance with applicable laws and the [Wizards of the Coast Fan Content Policy](https://company.wizards.com/en/legal/fancontentpolicy).

---

**Need Help?** Open an [issue](https://github.com/nicho92/mtg-ast-java/issues) on GitHub or check the [discussions](https://github.com/nicho92/mtg-ast-java/discussions).
