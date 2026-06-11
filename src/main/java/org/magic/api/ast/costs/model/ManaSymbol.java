package org.magic.api.ast.costs.model;

import java.util.List;

public enum ManaSymbol {

	WHITE ("W"),
	BLUE("U"),
	BLACK("B"),
	RED("R"),
	GREEN("G"),
	COLORLESS("C"),
	SNOW("S"),
	
	WU("W/U"),
	WB("W/B"),
	UB("U/B"),
	UR("U/R"),
	BR("B/R"),
	BG("B/G"),
	RG("R/G"),
	RW("R/W"),
	GW("G/W"),
	GU("G/U"),
	
	WHITE_PHYREXIAN("W/P"),
	BLUE_PHYREXIAN("U/P"),
	BLACK_PHYREXIAN("B/P"),
	RED_PHYREXIAN("R/P"),
	GREEN_PHYREXIAN("G/P"),
	COLORLESS_PHYREXIAN("C/P"),

	X("X"),
	
	NUMERIC("");
	
	private String data;
	
	ManaSymbol(String text) {
		this.data=text;
	}
	
	public boolean isX()
	{
		return this == X;
	}
	
	public boolean isSnow()
	{
		return this == SNOW;
	}
	
	public boolean isNumeric()
	{
		try {
			Integer.parseInt(getData());
			return true;
		}
		catch(Exception _)
		{
			return false;
		}
	}
	
	public Integer getIntegerValue()
	{
		return Integer.parseInt(getData());
	}
	
		
	public boolean isPhyrexian()
	{
		return getData().endsWith("/P");
	}
		
	public String getData() {
		return data;
	}
	
	void setData(String data) {
		this.data = data;
	}
		
	public static ManaSymbol parseByCode(String s) {
		
		try{
			var ret = NUMERIC;
			ret.setData(""+Integer.parseInt(s));
			return ret;
		}
		catch(Exception _)
		{
			return List.of(ManaSymbol.values())
						.stream()
						.filter(c -> c.getData().contains(s.toUpperCase().trim()))
						.findAny().orElse(null);
		}
	
	}

}