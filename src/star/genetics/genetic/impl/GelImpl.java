package star.genetics.genetic.impl;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;

import star.genetics.client.Helper;
import star.genetics.client.JSONable;
import star.genetics.client.JSONableList;
import star.genetics.genetic.model.Gel;
import star.genetics.genetic.model.GelPosition;

public class GelImpl implements Gel, Serializable
{
	private static final long serialVersionUID = 1L;
	private JSONObject data;

	public GelImpl(String name, int index)
	{
		data = new JSONObject();
		data.put(NAME, Helper.wrapString(name));
		data.put(INDEX, Helper.wrapNumber(index));
		data.put(SET, new JSONArray());
	}

	public GelImpl(JSONObject data)
    {
		this.data = data;
    }
	
	@Override
	public JSONObject getJSON()
	{
	    return data;
	}

	@Override
	public String getName()
	{

		return Helper.unwrapString(data.get(NAME));
	}

	@Override
	public int getIndex()
	{
		return Math.round(Helper.unwrapNumber(data.get(INDEX)));
	}

	JSONableList<GelPosition> set() {
		return new JSONableList<GelPosition>( data.get(SET).isArray())
		{

			@Override
            public GelPosition create(JSONObject data)
            {
	            return new GelPositionImpl(data);
            }
		};
	}
	
	public void addGelPosition(GelPosition gp)
	{
		set().add(gp);
	}

	@Override
	public Iterator<GelPosition> iterator()
	{
		// TODO Auto-generated method stub
		return set().iterator();
	}

}
