package org.magic.api.ast.interfaces.parsers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractParser<T> implements ASTParser<T> {

	private Pattern pattern = Pattern.compile(getPattern(),Pattern.CASE_INSENSITIVE);
	protected Logger logger = LogManager.getLogger(getClass());
	
	@Override
	public boolean supports(String text) {
		var find = pattern.matcher(text).matches();
		logger.debug("parsing {} with {} -> {}" , text, getPattern(),find);
		return find;
		
	}
	
	protected Matcher match(String text)
	{
		var matcher = pattern.matcher(text);
		matcher.find();
		return matcher;
	}

	protected abstract String getPattern();


}
