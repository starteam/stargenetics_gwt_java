package star.genetics.parser;

import java.util.logging.Level;
import java.util.logging.Logger;

import star.genetics.genetic.impl.ModelImpl;
import star.genetics.genetic.model.Model;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;

public class ModelParser
{
	static JSONObject getTop(String json)
	{
		JSONValue top = JSONParser.parseStrict(json);
		return top.isObject();
	}

	private static void parse(ModelImpl model, JSONObject top)
	{
		parseGenetics(model, top.get("genetics").isObject());
	}

	private static void parseGenetics(ModelImpl model, JSONObject genetics)
	{
		Logger logger = Logger.getLogger("Parse");
		logger.log( Level.INFO , "parse A");
		VisualizerParser.parse(model, genetics.get("visualizer").isObject());
		logger.log( Level.INFO , "parse B");
		GenomeParser.parse(model, genetics.get("genome").isObject());
		logger.log( Level.INFO , "parse C");
		EngineParser.parse(model, genetics.get("engine").isObject());
		logger.log( Level.INFO , "parse D");
		PhenotypeRulesParser.parse(model, genetics.get("phenotype_rules").isArray());
		logger.log( Level.INFO , "parse E");
		StrainsParser.parse(model, genetics.get("strains").isObject());
		logger.log( Level.INFO , "parse F");
	}

	public static Model parse(String json)
	{
		Logger logger = Logger.getLogger("Parse");
		logger.log( Level.INFO , "parse Start E");
		ModelImpl model = new ModelImpl();
		logger.log( Level.INFO , "parse 2");
		JSONObject top = getTop(json);
		logger.log( Level.INFO , "parse 3");
		parse(model, top);
		logger.log( Level.INFO , "parse End");
		return model;
	}

}
