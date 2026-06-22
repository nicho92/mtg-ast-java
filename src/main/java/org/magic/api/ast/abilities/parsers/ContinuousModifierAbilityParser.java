package org.magic.api.ast.abilities.parsers;

import org.magic.api.ast.abilities.ContinuousModifierAbility;
import org.magic.api.ast.factories.SelectorFactory;
import org.magic.api.ast.interfaces.AbilityNode;
import org.magic.api.ast.interfaces.parsers.AbilityParser;
import org.magic.api.ast.interfaces.parsers.AbstractParser;
import org.magic.api.ast.modifiers.PowerToughnessModifier;
import org.magic.api.ast.util.DurationParser;

public class ContinuousModifierAbilityParser extends AbstractParser<AbilityNode> implements AbilityParser {

	
	@Override
	protected String getPattern() {
		return "^(.+?)\\s+gets?\\s+([+-]\\d+)/([+-]\\d+)(.+?)\\.?$";
	}
	
	@Override
	public AbilityNode parse(String text) {

		var matcher = match(text);
		
		var duration="";
		if(matcher.group(4)!=null)
		{
			duration=matcher.group(4);
		}
		

		return new ContinuousModifierAbility(text,
															 SelectorFactory.INSTANCE.parse(matcher.group(1).trim()),
															 new PowerToughnessModifier(Integer.parseInt(matcher.group(2).replace('+', ' ').trim()), 
																	 									 Integer.parseInt(matcher.group(3).replace('+',' ').trim()),
																	 									 DurationParser.parse(duration)
																	 									 ));
	}
}
