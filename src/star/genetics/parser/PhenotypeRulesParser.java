package star.genetics.parser;

import java.util.HashMap;

import star.genetics.genetic.impl.ModelImpl;
import star.genetics.genetic.impl.RuleImpl;
import star.genetics.genetic.impl.RuleSetImpl;
import star.genetics.genetic.model.Genome;
import star.genetics.genetic.model.Rule;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;

public class PhenotypeRulesParser
{

	public static void parse(ModelImpl model, JSONArray rules)
    {
		RuleSetImpl set = new RuleSetImpl();
		for( int i = 0 ; i < rules.size(); i++)
		{
			JSONObject rule = rules.get(i).isObject();
			set.add(parseRule( set , rule , model.getGenome()));
		}
		model.setRules(set);
    }

	private static Rule parseRule(RuleSetImpl set, JSONObject rule, Genome genome)
    {
		String matches = rule.get("matches").isString().toString();
		HashMap<String,String> phenotype = parsePhenotype( rule.get("phenotype").isObject());
		return new RuleImpl(matches, phenotype , genome) ;
    }

	private static HashMap<String, String> parsePhenotype(JSONObject object)
    {
		HashMap<String, String> ret = new HashMap<String, String>();
		for(String key : object.keySet() )
		{
			ret.put( key , object.get(key).toString());
		}
		return ret;
    }

}
