package org.magic.api.ast.test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.magic.api.ast.engine.OracleParser;

class LoadOraclesTest {

	
	
	private List<String> loadData() throws Exception
	{
		
		var content =  Files.readString(Path.of(LoadOraclesTest.class.getResource("/oracles.txt").toURI()));
		
		return Arrays.stream(content.split("(?m)^===\\s*$"))
	                .map(String::trim)
	                .filter(s -> !s.isEmpty())
	                .toList();
	}
	
	
	@Test
	 void test() throws Exception
	{
		for(var s : loadData())
		{
			OracleParser.toFacade("cardname", s).getAllAbilities().forEach(System.out::println);
			System.out.println("=========");
		}
	}
}
