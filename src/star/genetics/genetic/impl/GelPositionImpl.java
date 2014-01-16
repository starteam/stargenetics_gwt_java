package star.genetics.genetic.impl;

import java.io.Serializable;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;

import star.genetics.client.Helper;
import star.genetics.genetic.model.Allele;
import star.genetics.genetic.model.Gel;
import star.genetics.genetic.model.GelPosition;

public class GelPositionImpl implements GelPosition, Serializable
{
	private static final long serialVersionUID = 1L;
	private JSONObject data;

	public GelPositionImpl(Gel gel, Float[] position, Allele allele)
	{
		data = new JSONObject();
		data.put(GEL, gel.getJSON());
		setPosition(position);
		data.put(ALLELES, allele.getJSON());
	}

	public GelPositionImpl(JSONObject data)
	{
		this.data = data;
	}

	@Override
	public JSONObject getJSON()
	{
	    return data;
	}
	
	@Override
	public Gel getGel()
	{
		return new GelImpl(data.get(GEL).isObject());
	}

	@Override
	public Float[] getPosition()
	{
		JSONArray array = data.get(POSITION).isArray();
		Float[] ret = new Float[array.size()];
		for (int i = 0; i < array.size(); i++)
		{
			ret[i] = Helper.unwrapNumber(array.get(i));
		}
		return ret;
	}

	void setPosition(Float[] position)
	{
		JSONArray array = new JSONArray();
		int index = 0;
		for (float f : position)
		{
			array.set(index++, Helper.wrapNumber(f));
		}
		data.put(POSITION, array);

	}

	@Override
	public Allele getAllele()
	{
		return new AlleleImpl(data.get(ALLELES).isObject());
	}

}
