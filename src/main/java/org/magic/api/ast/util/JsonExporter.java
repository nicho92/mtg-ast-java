package org.magic.api.ast.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

import org.magic.api.ast.engine.CardAst;
import org.magic.api.ast.interfaces.KeywordRegistry;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

public class JsonExporter {

	private Gson gson;

	public JsonExporter() {
		gson = new GsonBuilder().setPrettyPrinting().create();
	}

	public String export(CardAst card) {
		return gson.toJson(card);
	}

	public CardAst importFromJson(String json) {
		return gson.fromJson(json, CardAst.class);
	}

	public KeywordRegistry getMTGJsonRegistry() throws JsonSyntaxException, JsonIOException, IOException {
		var results = gson
				.fromJson(
						new InputStreamReader(
								URI.create("https://mtgjson.com/api/v5/Keywords.json").toURL().openStream()),
						JsonObject.class)
				.get("data").getAsJsonObject().get("keywordAbilities").getAsJsonArray().asList().stream()
				.map(je -> je.getAsString()).toList();

		return () -> results;
	}

}
