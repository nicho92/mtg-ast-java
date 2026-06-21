package org.magic.api.ast.test;

import org.junit.jupiter.api.Test;
import org.magic.api.ast.costs.LifeCost;
import org.magic.api.ast.costs.ManaValue;
import org.magic.api.ast.costs.SacrificeCost;
import org.magic.api.ast.costs.TapCost;
import org.magic.api.ast.effects.CreateTokenEffect;
import org.magic.api.ast.effects.DealDamageEffect;
import org.magic.api.ast.effects.DestroyTargetEffect;
import org.magic.api.ast.effects.DrawCardsEffect;
import org.magic.api.ast.effects.MillEffect;
import org.magic.api.ast.effects.UnknownEffect;
import org.magic.api.ast.engine.OracleParser;

class UnitTest {
	
	@Test
	 void test()
	 {
			var s = """
						Flying
						When this creature enters, draw a card.
						{1}{U},{T}:  Create a 1/1 colorless Shapeshifter creature token.
					""";

			var f = OracleParser.toFacade("Urza", s);
					
			for (var ability : f.getActivatedAbilities()) {
			    System.out.println("\n--- Analyse : \"" + ability.text() + "\" ---");
			    System.out.println("Coûts de la capacité :");
			    for (var cost : ability.costs()) {
			        switch (cost) {
			            // Pour TapCost (qui est une classe standard)
			            case TapCost tap -> 
			                System.out.println(" Tap - : " + tap.getType());
			            // Déstructuration directe des records
			            case SacrificeCost( _, var target, int qty) -> 
			                System.out.println("  - Sacrifier : " + qty + " x " + target);
			            case LifeCost( _, int qty) -> 
			                System.out.println("  - Points de vie à payer : " + qty + " PV");
			            case ManaValue( _, var manaList) -> 
			                System.out.println("  - Coût en mana : " + manaList);
			        }
			    }
			    System.out.println("Effets de la capacité :");
			    for (var effect : ability.effects()) {
			        switch (effect) {
			            case DrawCardsEffect(var _, int amount) -> 
			                System.out.println("  - Piocher : " + amount + " carte(s)");
			            case DealDamageEffect(var _, var _, int amount, var target) -> 
			                System.out.println("  - Dégâts : " + amount + " blessures à " + target);
			            case MillEffect(var _, int amount) -> 
			                System.out.println("  - Meule : " + amount + " carte(s)");
			            case DestroyTargetEffect(var _, var target) -> 
			                System.out.println("  - Détruire : " + target);
			            case CreateTokenEffect(String _, String tokenDescription, int quantity)->   
			            System.out.println("  - Creation de "+ quantity + "token : " + tokenDescription);
			            case UnknownEffect(var txt) -> System.out.println("  - Effet non typé : " + txt);    
			            
					default -> System.out.println("Unexpected value: " + effect);
			                
			            // (Note : Pas besoin de clause default si toutes les implémentations scellées d'EffectNode sont traitées)
			        }
			    }
			}
			
			
	}
	
}