package org.magic.api.ast.interfaces.parsers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractParser<T> implements ASTParser<T> {

	protected Pattern PATTERN = Pattern.compile(getPattern(),Pattern.CASE_INSENSITIVE);

	@Override
	public boolean supports(String text) {
		return PATTERN.matcher(text).matches();
	}
	
	protected Matcher match(String text)
	{
		var matcher = PATTERN.matcher(text);

		matcher.find();
		
		return matcher;
	}
	

	protected abstract String getPattern();


}
