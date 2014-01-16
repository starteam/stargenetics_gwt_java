package star.genetics.genetic.impl;

import java.io.Serializable;

import com.google.gwt.json.client.JSONObject;

import star.genetics.genetic.model.Creature.Sex;
import star.genetics.genetic.model.GeneticMakeup;

public class SexRuleImpl implements IndividualRule, Serializable
{
	private static final long serialVersionUID = 1L;

	private Sex s()
	{
		return Sex.fromJSON(data.get(SEX));
	};

	private JSONObject data;

	public SexRuleImpl(Sex s)
	{
		data = new JSONObject();
		data.put(SEX, s.getJSON());
	}

	public SexRuleImpl(JSONObject data)
	{
		this.data = data;
	}
	
	@Override
	public JSONObject getJSON()
	{
	    return data;
	}

	public boolean test(GeneticMakeup makeup, Sex sex)
	{

		return s().equals(sex);
	}

}
