package star.genetics.genetic.impl;

import java.io.Serializable;

import star.genetics.client.Helper;
import star.genetics.client.MessageFormat;
import star.genetics.client.Messages;
import star.genetics.genetic.model.CrateModel;
import star.genetics.genetic.model.CreatureSet;

import com.google.gwt.json.client.JSONObject;

public class CrateModelImpl implements CrateModel, Serializable
{
	private static final long serialVersionUID = 1L;
	private JSONObject data;

	public CrateModelImpl(int id)
	{
		data.put(PARENTS, new JSONObject());
		data.put(PROGENIES, new JSONObject());
		data.put(NAME, Helper.wrapString(MessageFormat.format(Messages.getString("CrateModelImpl.0"), id))); //$NON-NLS-1$
		data.put(UUID, Helper.wrapString(generateUUID()));
	}

	CrateModelImpl(JSONObject data)
	{
		this.data = data;
	}

	@Override
	public JSONObject getJSON()
	{
		return data;
	}

	public void setName(String name)
	{
		data.put(NAME, Helper.wrapString(name));
	}

	public String getName()
	{
		return Helper.unwrapString(data.get(NAME));
	}

	public CreatureSet getParents()
	{
		return new CreatureSetImpl(data.get(PARENTS).isObject());
	}

	public CreatureSet getProgenies()
	{
		return new CreatureSetImpl(data.get(PROGENIES).isObject());
	}

	@Override
	public String toString()
	{
		return getName();
	}

	private String generateUUID()
	{
		long uuid1 = -(long) (Math.random() * Long.MAX_VALUE);
		long uuid2 = -(long) (Math.random() * Long.MAX_VALUE);
		return Long.toHexString(uuid1) + Long.toHexString(uuid2);

	}

	@Override
	public String getUUID()
	{
		return Helper.unwrapString(data.get(UUID));
	}

}
