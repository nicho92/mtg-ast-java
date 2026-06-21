# MTG AST Java

[![Java](https://img.shields.io/badge/Java-25%2B-orange.svg)](https://www.oracle.com/java/)
[![Maven](https://img.shields.io/badge/build-Maven-blue.svg)](https://maven.apache.org/)
[![License](https://img.shields.io/badge/license-Apache%202.0-green.svg)](http://www.apache.org/licenses/LICENSE-2.0)
[![GitHub](https://img.shields.io/badge/GitHub-nicho92%2Fmtg--ast--java-blue)](https://github.com/nicho92/mtg-ast-java)

**MTG AST Java** is a lightweight, powerful Java library for parsing *Magic: The Gathering* oracle text into a structured abstract syntax tree (AST). It transforms complex card abilities into strongly-typed Java records, enabling programmatic analysis, traversal, and manipulation of MTG card mechanics.

---

## 🎯 Features

- **Comprehensive Parsing**: Parse multi-line oracle text using [OracleParser](file:///d:/programmation/GIT/mtg-card-analyzer/src/main/java/org/magic/api/ast/engine/OracleParser.java) and convert it into a structured facade [ASTFacade](file:///d:/programmation/GIT/mtg-card-analyzer/src/main/java/org/magic/api/ast/util/ASTFacade.java).
- **Strongly-Typed Model**: Modeled using modern Java features (records, sealed interfaces, and pattern matching).
- **Ability Types**: Detect and parse keyword abilities, activated/triggered abilities, continuous modifiers, replacement effects, planeswalker loyalty abilities, saga chapters, and ability-word prefixed abilities.
- **Unified Triggers & Selectors**: Express triggers with [Trigger](file:///d:/programmation/GIT/mtg-card-analyzer/src/main/java/org/magic/api/ast/triggers/Trigger.java) and target subjects with specialized [SelectorNode](file:///d:/programmation/GIT/mtg-card-analyzer/src/main/java/org/magic/api/ast/interfaces/SelectorNode.java) implementations.
- **Serialization**: Export the AST to JSON and import it back to Java records via [JsonExporter](file:///d:/programmation/GIT/mtg-card-analyzer/src/main/java/org/magic/api/ast/util/JsonExporter.java).
- **Dynamic Keywords**: Fetch or import keyword lists dynamically from the MTGJson Keyword API.
- **Visitor Pattern**: Easily traverse the AST without `instanceof` cascades using [AbilityVisitor](file:///d:/programmation/GIT/mtg-card-analyzer/src/main/java/org/magic/api/ast/interfaces/visitors/AbilityVisitor.java).

---

## 📋 Requirements

- **Java 25** or newer (configured by `maven.compiler.release` in [pom.xml](file:///d:/programmation/GIT/mtg-card-analyzer/pom.xml))
- **Apache Maven 3.9+**

---

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

Add the following dependency to your [pom.xml](file:///d:/programmation/GIT/mtg-card-analyzer/pom.xml):

```xml
<dependency>
  <groupId>com.github.nicho92</groupId>
  <artifactId>mtg-ast-java</artifactId>
  <version>0.0.16</version>
</dependency>
```

---

## 🚀 Quick Start

### Parsing & Pattern Matching

The following example demonstrates how to parse oracle text, obtain an [ASTFacade](file:///d:/programmation/GIT/mtg-card-analyzer/src/main/java/org/magic/api/ast/util/ASTFacade.java), and use Java pattern matching to inspect the AST:

```java
import org.magic.api.ast.engine.OracleParser;
import org.magic.api.ast.util.ASTFacade;
import org.magic.api.ast.abilities.ActivatedAbility;
import org.magic.api.ast.costs.TapCost;
import org.magic.api.ast.costs.SacrificeCost;
import org.magic.api.ast.costs.LifeCost;
import org.magic.api.ast.costs.ManaValue;
import org.magic.api.ast.effects.DrawCardsEffect;
import org.magic.api.ast.effects.DealDamageEffect;
import org.magic.api.ast.effects.MillEffect;
import org.magic.api.ast.effects.DestroyTargetEffect;
import org.magic.api.ast.effects.CreateTokenEffect;
import org.magic.api.ast.effects.UnknownEffect;

public class Main {
    public static void main(String[] args) {
        String oracleText = """
                Flying
                When this creature enters, draw a card.
                {1}{U},{T}: Create a 1/1 colorless Shapeshifter creature token.
                """;

        ASTFacade facade = OracleParser.toFacade("Urza, Lord High Artificer", oracleText);

        System.out.println("Card Name: " + facade.getCardName());

        // Process Activated Abilities
        for (ActivatedAbility ability : facade.getActivatedAbilities()) {
            System.out.println("\n--- Analyze: \"" + ability.text() + "\" ---");
            
            System.out.println("Costs:");
            for (var cost : ability.costs()) {
                switch (cost) {
                    case TapCost tap -> System.out.println("  - Tap: " + tap.getType());
                    case SacrificeCost(var _, var target, int qty) -> 
                        System.out.println("  - Sacrifice: " + qty + "x " + target);
                    case LifeCost(var _, int qty) -> 
                        System.out.println("  - Pay Life: " + qty + " HP");
                    case ManaValue(var _, var manaSymbols) -> 
                        System.out.println("  - Mana Cost: " + manaSymbols);
                }
            }

            System.out.println("Effects:");
            for (var effect : ability.effects()) {
                switch (effect) {
                    case DrawCardsEffect(var _, int amount) -> 
                        System.out.println("  - Draw: " + amount + " card(s)");
                    case DealDamageEffect(var _, var _, int amount, var target) -> 
                        System.out.println("  - Deal Damage: " + amount + " to " + target);
                    case MillEffect(var _, int amount) -> 
                        System.out.println("  - Mill: " + amount + " card(s)");
                    case DestroyTargetEffect(var _, var target) -> 
                        System.out.println("  - Destroy: " + target);
                    case CreateTokenEffect(var _, String desc, int qty) -> 
                        System.out.println("  - Create Token: " + qty + "x " + desc);
                    case UnknownEffect(var text) -> 
                        System.out.println("  - Unknown Effect: " + text);
                    default -> 
                        System.out.println("  - Other: " + effect);
                }
            }
        }
    }
}
```

### JSON Export & Import

You can serialize the AST model to JSON and reconstruct it back using [JsonExporter](file:///d:/programmation/GIT/mtg-card-analyzer/src/main/java/org/magic/api/ast/util/JsonExporter.java):

```java
import org.magic.api.ast.engine.CardAst;
import org.magic.api.ast.engine.OracleParser;
import org.magic.api.ast.util.JsonExporter;

// Parse a card and get the raw CardAst
CardAst cardAst = new OracleParser().parse("Urza", "Flying\n{T}: Add {W}.");

// Export to JSON string
JsonExporter exporter = new JsonExporter();
String json = exporter.export(cardAst);
System.out.println(json);

// Import from JSON string
CardAst importedCard = exporter.importFromJson(json);
```

### Visitor Traversal

Traverse abilities dynamically using [AbilityVisitor](file:///d:/programmation/GIT/mtg-card-analyzer/src/main/java/org/magic/api/ast/interfaces/visitors/AbilityVisitor.java) and [AbstractVisitor](file:///d:/programmation/GIT/mtg-card-analyzer/src/main/java/org/magic/api/ast/interfaces/visitors/AbstractVisitor.java):

```java
import org.magic.api.ast.interfaces.AbilityNode;
import org.magic.api.ast.visitor.impl.AbilityDescriber;

var describer = new AbilityDescriber();
for (AbilityNode ability : facade.getAllAbilities()) {
    String description = ability.accept(describer);
    System.out.println(description);
}
```

---

## 📚 Supported AST Model

### Card Structure

| Type | Description |
| --- | --- |
| [CardAst](file:///d:/programmation/GIT/mtg-card-analyzer/src/main/java/org/magic/api/ast/engine/CardAst.java) | Root object containing the card name and its parsed list of abilities. |
| [ASTFacade](file:///d:/programmation/GIT/mtg-card-analyzer/src/main/java/org/magic/api/ast/util/ASTFacade.java) | Utility wrapper offering type-safe query, filtering, and trigger extraction. |

### Ability Types

| Type | Description | Example |
| --- | --- | --- |
| [KeywordsAbility](file:///d:/programmation/GIT/mtg-card-analyzer/src/main/java/org/magic/api/ast/abilities/KeywordsAbility.java) | Holds keyword abilities, optionally parameterized. | `Flying`, `Ward {2}` |
| [ActivatedAbility](file:///d:/programmation/GIT/mtg-card-analyzer/src/main/java/org/magic/api/ast/abilities/ActivatedAbility.java) | One or more costs leading to one or more effects. | `{T}: Add {G}.` |
| [TriggeredAbility](file:///d:/programmation/GIT/mtg-card-analyzer/src/main/java/org/magic/api/ast/abilities/TriggeredAbility.java) | A trigger paired with one or more effects. | `When this enters, draw a card.` |
| [StaticAbility](file:///d:/programmation/GIT/mtg-card-analyzer/src/main/java/org/magic/api/ast/abilities/StaticAbility.java) | Fallback node for oracle text that doesn't match other parser patterns. | `This spell can't be countered.` |
| [ModalAbility](file:///d:/programmation/GIT/mtg-card-analyzer/src/main/java/org/magic/api/ast/abilities/ModalAbility.java) | A choice constraint with bullet modes and parsed effects. | `Choose one — Draw a card. or Discard a card.` |
| [ReplacementEffectAbility](file:///d:/programmation/GIT/mtg-card-analyzer/src/main/java/org/magic/api/ast/abilities/ReplacementEffectAbility.java) | Replaced events and replacement effects. | `If a creature would die, ...` |
| [ContinuousModifierAbility](file:///d:/programmation/GIT/mtg-card-analyzer/src/main/java/org/magic/api/ast/abilities/ContinuousModifierAbility.java) | Static modifiers like power/toughness changes. | `Creatures you control get +1/+1.` |
| [PlaneswalkerAbility](file:///d:/programmation/GIT/mtg-card-analyzer/src/main/java/org/magic/api/ast/abilities/PlaneswalkerAbility.java) | Loyalty-activated ability containing cost (loyalty adjustment) and effects. | `[+1]: Put a +1/+1 counter on up to one target creature.` |
| [SagaAbility](file:///d:/programmation/GIT/mtg-card-analyzer/src/main/java/org/magic/api/ast/abilities/SagaAbility.java) | Saga chapter abilities with chapter number and effects. | `I, II — Draw a card.` |
| [SpellAbility](file:///d:/programmation/GIT/mtg-card-analyzer/src/main/java/org/magic/api/ast/abilities/SpellAbility.java) | Represents the instant/sorcery spell effect itself. | `Lightning Bolt deals 3 damage to any target.` |
| [WordAbility](file:///d:/programmation/GIT/mtg-card-analyzer/src/main/java/org/magic/api/ast/abilities/WordAbility.java) | Ability prefixed by an ability word. | `Landfall — Whenever a land enters...` |

### Cost Types

| Type | Example |
| --- | --- |
| [ManaValue](file:///d:/programmation/GIT/mtg-card-analyzer/src/main/java/org/magic/api/ast/costs/ManaValue.java) | `{2}{G}` |
| [TapCost](file:///d:/programmation/GIT/mtg-card-analyzer/src/main/java/org/magic/api/ast/costs/TapCost.java) | `{T}` |
| [SacrificeCost](file:///d:/programmation/GIT/mtg-card-analyzer/src/main/java/org/magic/api/ast/costs/SacrificeCost.java) | `Sacrifice two artifacts` |
| [LifeCost](file:///d:/programmation/GIT/mtg-card-analyzer/src/main/java/org/magic/api/ast/costs/LifeCost.java) | `Pay 1 life` |

### Effect Types

- [AddManaEffect](file:///d:/programmation/GIT/mtg-card-analyzer/src/main/java/org/magic/api/ast/effects/AddManaEffect.java) - `Add {G}.`
- [CreateTokenEffect](file:///d:/programmation/GIT/mtg-card-analyzer/src/main/java/org/magic/api/ast/effects/CreateTokenEffect.java) - `Create a 1/1 white Soldier creature token.`
- [DealDamageEffect](file:///d:/programmation/GIT/mtg-card-analyzer/src/main/java/org/magic/api/ast/effects/DealDamageEffect.java) - `Deal 3 damage to any target.`
- [DestroyTargetEffect](file:///d:/programmation/GIT/mtg-card-analyzer/src/main/java/org/magic/api/ast/effects/DestroyTargetEffect.java) - `Destroy target creature.`
- [DiscardCardsEffect](file:///d:/programmation/GIT/mtg-card-analyzer/src/main/java/org/magic/api/ast/effects/DiscardCardsEffect.java) - `Each opponent discards a card.`
- [DrawCardsEffect](file:///d:/programmation/GIT/mtg-card-analyzer/src/main/java/org/magic/api/ast/effects/DrawCardsEffect.java) - `Draw a card.`
- [ExileEffect](file:///d:/programmation/GIT/mtg-card-analyzer/src/main/java/org/magic/api/ast/effects/ExileEffect.java) - `Exile target creature.`
- [ExtraTurnEffect](file:///d:/programmation/GIT/mtg-card-analyzer/src/main/java/org/magic/api/ast/effects/ExtraTurnEffect.java) - `Take an extra turn after this one.`
- [GainLifeEffect](file:///d:/programmation/GIT/mtg-card-analyzer/src/main/java/org/magic/api/ast/effects/GainLifeEffect.java) - `You gain 3 life.`
- [GetEmblemEffect](file:///d:/programmation/GIT/mtg-card-analyzer/src/main/java/org/magic/api/ast/effects/GetEmblemEffect.java) - `You get an emblem with ...`
- [MillEffect](file:///d:/programmation/GIT/mtg-card-analyzer/src/main/java/org/magic/api/ast/effects/MillEffect.java) - `Target player mills two cards.`
- [PutCountersEffect](file:///d:/programmation/GIT/mtg-card-analyzer/src/main/java/org/magic/api/ast/effects/PutCountersEffect.java) - `Put a +1/+1 counter on target creature.`
- [ReturnEffect](file:///d:/programmation/GIT/mtg-card-analyzer/src/main/java/org/magic/api/ast/effects/ReturnEffect.java) - `Return target creature card from your graveyard...`
- [SacrificeEffect](file:///d:/programmation/GIT/mtg-card-analyzer/src/main/java/org/magic/api/ast/effects/SacrificeEffect.java) - `Each opponent sacrifices a creature.`
- [UnknownEffect](file:///d:/programmation/GIT/mtg-card-analyzer/src/main/java/org/magic/api/ast/effects/UnknownEffect.java) - Fallback for unsupported effect patterns.

### Selector Types

Selector nodes are used to identify the target or subject of costs, effects, and triggers:

- [ArtifactSelector](file:///d:/programmation/GIT/mtg-card-analyzer/src/main/java/org/magic/api/ast/selectors/ArtifactSelector.java) - References artifact targets.
- [CompoundSelector](file:///d:/programmation/GIT/mtg-card-analyzer/src/main/java/org/magic/api/ast/selectors/CompoundSelector.java) - Combines multiple selectors.
- [CreatureSelector](file:///d:/programmation/GIT/mtg-card-analyzer/src/main/java/org/magic/api/ast/selectors/CreatureSelector.java) - References creature targets.
- [LandSelector](file:///d:/programmation/GIT/mtg-card-analyzer/src/main/java/org/magic/api/ast/selectors/LandSelector.java) - References land targets.
- [PlayerSelector](file:///d:/programmation/GIT/mtg-card-analyzer/src/main/java/org/magic/api/ast/selectors/PlayerSelector.java) - References players (e.g., "you", "target opponent").
- [SelfSelector](file:///d:/programmation/GIT/mtg-card-analyzer/src/main/java/org/magic/api/ast/selectors/SelfSelector.java) - References the card itself.
- [SubtypeSelector](file:///d:/programmation/GIT/mtg-card-analyzer/src/main/java/org/magic/api/ast/selectors/SubtypeSelector.java) - References targets with a specific subtype.
- [TappedSelector](file:///d:/programmation/GIT/mtg-card-analyzer/src/main/java/org/magic/api/ast/selectors/TappedSelector.java) - References tapped targets.
- [TextSelector](file:///d:/programmation/GIT/mtg-card-analyzer/src/main/java/org/magic/api/ast/selectors/TextSelector.java) - References arbitrary custom target text.

### Triggers

Triggers are unified under the [Trigger](file:///d:/programmation/GIT/mtg-card-analyzer/src/main/java/org/magic/api/ast/triggers/Trigger.java) record, composed of a [SelectorNode](file:///d:/programmation/GIT/mtg-card-analyzer/src/main/java/org/magic/api/ast/interfaces/SelectorNode.java) subject and a [TriggerType](file:///d:/programmation/GIT/mtg-card-analyzer/src/main/java/org/magic/api/ast/triggers/TriggerType.java) enum value.

**Common Trigger Types:**
- `ENTERS_BATTLEFIELD`, `LEAVES_BATTLEFIELD`, `DIES`
- `ATTACKS`, `BLOCKS`, `BECOMES_BLOCKED`
- `DEALS_DAMAGE`, `RECEIVES_DAMAGE`
- `SPELL_CAST`, `SPELL_COUNTERED`
- `DRAW_CARD`, `DISCARD_CARD`
- `GAIN_LIFE`, `LOSE_LIFE`
- `SACRIFICE`, `EXILE`, `DESTROY`
- `BEGINNING_OF_UPKEEP`, `BEGINNING_OF_END_STEP`
- `BECOMES_TARGET`
- `COUNTER_ADDED`, `COUNTER_REMOVED`
- `SAGA_CHAPTER`, `CLASS_LEVEL`
- `UNKNOW` (Fallback)

---

## 📁 Project Structure

```
src/main/java/org/magic/api/ast/
├── abilities/      # Ability nodes, submodels, and ability parsers
├── costs/          # Cost nodes, submodels, and cost parsers
├── effects/        # Effect nodes and effect parsers
├── engine/         # OracleParser and CardAst entry points
├── factories/      # Factory classes for parsing capabilities
├── interfaces/     # Core AST nodes, parser interfaces, visitors & keyword registry
├── modifiers/      # Modifier implementations (e.g. PowerToughnessModifier)
├── selectors/      # Target and source selector nodes
├── triggers/       # Trigger node and TriggerType enum
├── util/           # Utilities (ASTFacade, JsonExporter, DefaultKeywordRegistry)
└── visitor/        # Traversal implementations (AbilityDescriber, EffectCollector, etc.)
```

---

## 🔧 Development

### Build the Project

```bash
mvn clean package
```

### Run Tests and Samples

```bash
mvn exec:java -Dexec.mainClass=org.magic.api.ast.main.Test
```

---

## 📅 Roadmap

- [ ] Add comprehensive unit tests for all parsers and AST node types
- [ ] Expand parser coverage for:
  - Complex replacement effects conditions
  - Nested modal choices
  - Counter mechanics and interactions
  - Combat restrictions (e.g., `can't block`)
  - Advanced target clauses
- [x] Publish releases to Sonatype Maven Central
- [ ] Generate and host API documentation
- [ ] Add support for older MTG card text variations
- [ ] Performance optimizations for batch parsing

---

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
- Update relevant sections in the [README.md](file:///d:/programmation/GIT/mtg-card-analyzer/README.md)
- Follow the existing code style and structure

---

## 📄 License

This project is licensed under the **Apache License, Version 2.0**.  
See the [pom.xml](file:///d:/programmation/GIT/mtg-card-analyzer/pom.xml) file for complete license details.

For more information, visit: [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0)

---

## ⚖️ Disclaimer

This project is an **unofficial fan-made tool** and is not affiliated with, endorsed by, sponsored by, or specifically approved by Wizards of the Coast LLC.

*Magic: The Gathering* is a registered trademark of Wizards of the Coast LLC.  
All MTG card data and mechanics are property of Wizards of the Coast LLC.

This library is provided for educational and fan purposes only. Use responsibly and in accordance with applicable laws and the [Wizards of the Coast Fan Content Policy](https://company.wizards.com/en/legal/fancontentpolicy).

---

**Need Help?** Open an [issue](https://github.com/nicho92/mtg-ast-java/issues) on GitHub or check the [discussions](https://github.com/nicho92/mtg-ast-java/discussions).
