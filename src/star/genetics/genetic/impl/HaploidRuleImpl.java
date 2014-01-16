package star.genetics.genetic.impl;

import com.google.gwt.json.client.JSONNull;
import com.google.gwt.json.client.JSONObject;

import star.genetics.genetic.model.Creature.Sex;
import star.genetics.genetic.model.GeneticMakeup;

public class HaploidRuleImpl implements IndividualRule
{
	private static final long serialVersionUID = 1L;

	public boolean test(GeneticMakeup makeup, Sex sex)
	{

		return sex != null;
	}

	@Override
	public JSONObject getJSON()
	{
	    return new JSONObject();
	}
	
	public HaploidRuleImpl(JSONObject data)
    {
    }
	
	public HaploidRuleImpl()
	{
		
	}
}
