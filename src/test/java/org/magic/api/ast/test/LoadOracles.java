package org.magic.api.ast.test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.magic.api.ast.engine.OracleParser;

class LoadOracles {

	
	
	public List<String> loadOracle() throws Exception
	{
		
		var content =  Files.readString(Path.of(LoadOracles.class.getResource("/oracles.txt").toURI()));
		
		return Arrays.stream(content.split("(?m)^===\\s*$"))
	                .map(String::trim)
	                .filter(s -> !s.isEmpty())
	                .toList();
	}
	
	
	@Test
	 void test() throws Exception
	{
		var parser =  new OracleParser();
		
		for(var s : loadOracle())
		{
			System.out.println(s);
			System.out.println();
			
			parser.parse("card", s).getAbilities().forEach(a->{
				System.out.println(a);
			});
			
			System.out.println("=========");
		}
	}
}
