package org.magic.api.ast.effects.parsers;

import org.magic.api.ast.effects.MillEffect;
import org.magic.api.ast.interfaces.EffectNode;
import org.magic.api.ast.interfaces.parsers.AbstractParser;
import org.magic.api.ast.interfaces.parsers.EffectParser;
import org.magic.api.ast.util.AmountParser;

public class MillEffectParser extends AbstractParser<EffectNode> implements EffectParser {

	
	@Override
	protected String getPattern() {
		return  "^(?<target>.+?)\\s+(?:mills?\\s+(?<amount1>.*?)\\s+cards?|puts?\\s+the\\s+top\\s+(?<amount2>.*?)\\s+cards?\\s+of\\s+(?:his\\s+or\\s+her|their|your)\\s+library\\s+into\\s+(?:his\\s+or\\s+her|their|your)\\s+graveyard)\\.?$";
	}

    @Override
    public EffectNode parse(String text) {
        var matcher = match(text);
        
        var amount =  matcher.group("amount1") != null
                ? matcher.group("amount1")
                        : matcher.group("amount2");
        
        
      	return new MillEffect(text,AmountParser.parse(amount));
    	
        	
    }
}