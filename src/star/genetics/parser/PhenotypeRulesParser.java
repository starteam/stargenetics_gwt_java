package star.genetics.parser;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import star.genetics.genetic.impl.ModelImpl;
import star.genetics.genetic.impl.RuleImpl;
import star.genetics.genetic.impl.RuleSetImpl;
import star.genetics.genetic.model.Genome;
import star.genetics.genetic.model.Rule;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;

public class PhenotypeRulesParser
{
	static Logger logger = Logger.getLogger("PhenotypeRulesParser");

	public static void parse(ModelImpl model, JSONArray rules)
	{
		logger.log(Level.INFO, "step 1");

		RuleSetImpl set = new RuleSetImpl(model);
		logger.log(Level.INFO, "step 2");
		for (int i = 0; i < rules.size(); i++)
		{
			logger.log(Level.INFO, "step 3");

			JSONObject rule = rules.get(i).isObject();
			logger.log(Level.INFO, "step 4 QQ");

			set.add(parseRule(set, rule, model.getGenome(),model));
			logger.log(Level.INFO, "step 5 " + i + " " + rules.size());
		}
		logger.log(Level.INFO, "step 6");
		model.setRules(set);
		logger.log(Level.INFO, "step 7");

	}

	private static Rule parseRule(RuleSetImpl set, JSONObject rule, Genome genome, ModelImpl model)
	{
		logger.log(Level.INFO, "step 8");
		String matches = rule.get("matches").isString().stringValue();
		HashMap<String, String> phenotype = parsePhenotype(rule.get("phenotype").isObject());
		logger.log(Level.INFO, "step 9");
		RuleImpl ret = new RuleImpl(matches, phenotype, genome,model);
		logger.log(Level.INFO, "step 10");
		return ret;
	}

	private static HashMap<String, String> parsePhenotype(JSONObject object)
	{
		logger.log(Level.INFO, "step 11");
		HashMap<String, String> ret = new HashMap<String, String>();
		for (String key : object.keySet())
		{
			ret.put(key, object.get(key).toString());
		}
		logger.log(Level.INFO, "step 12");
		return ret;
	}

}
