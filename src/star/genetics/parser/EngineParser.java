package star.genetics.parser;

import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;

import star.genetics.genetic.impl.MatingEngineImpl_XY;
import star.genetics.genetic.impl.ModelImpl;

public class EngineParser
{
	public static void parse(ModelImpl model, JSONObject engine)
	{
		String sex_type = engine.get("sex_type").isString().toString();
		if ("xy".equalsIgnoreCase(sex_type))
		{
			parse_XY(model, engine);
		}
	}

	private static float get(JSONObject obj, String key, float default_value)
	{
		float ret = default_value;
		if (obj.containsKey(key))
		{
			JSONNumber n = obj.get(key).isNumber();
			if (n != null)
			{
				ret = (float) n.doubleValue();
			}
		}
		return ret;
	}

	private static void parse_XY(ModelImpl model, JSONObject engine)
	{
		float maleRecombinationRate = get(engine, "male_recombination_rate", 1.0f);
		float femaleRecombinationRate = get(engine, "female_recombination_rate", 1.0f);
		float femaleSexRatio = get(engine, "female_sex_ratio", 1.0f);
		int progeniesCount = Math.round(get(engine, "avg_offspring_count", 50.0f));
		float twinningFrequency = get(engine, "twinning", 0.0f);
		float identicalTwinsFrequency = get(engine, "identical_twins_frequency", 0.0f);

		MatingEngineImpl_XY xy = new MatingEngineImpl_XY(maleRecombinationRate, femaleRecombinationRate, femaleSexRatio, progeniesCount, twinningFrequency, identicalTwinsFrequency);
		model.setMater(xy);
	}
}
