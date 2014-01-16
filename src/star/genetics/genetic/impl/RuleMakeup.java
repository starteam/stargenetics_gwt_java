package star.genetics.genetic.impl;

import java.util.Set;

import star.genetics.client.JSONable;
import star.genetics.genetic.model.DiploidAlleles;
import star.genetics.genetic.model.Gene;

import com.google.gwt.json.client.JSONObject;

public class RuleMakeup implements JSONable {

	private JSONObject data;

	RuleMakeup( JSONObject data )
	{
		this.data = data;
	}
	
	@Override
	public JSONObject getJSON()
	{
	    return data;
	}
	
	public void put(Gene g, DiploidAlleles d)
	{
		data.get(MAKEUP).isObject().put(g.getJSON().toString(), d.getJSON());
	}

	public DiploidAlleles get(Gene g)
	{
		return new DiploidAllelesImpl(data.get(MAKEUP).isObject().get(g.getJSON().toString()).isObject());
	}

	public DiploidAlleles get(String g)
	{
		return new DiploidAllelesImpl(data.get(MAKEUP).isObject().get(g).isObject());
	}

	int size()
	{
		return data.get(MAKEUP).isObject().size();
	}
	
	public Set<String> keySet() {
		return data.get(MAKEUP).isObject().keySet();
	}
	
}