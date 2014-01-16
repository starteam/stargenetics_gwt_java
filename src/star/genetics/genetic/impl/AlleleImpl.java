package star.genetics.genetic.impl;

import java.io.Serializable;

import star.genetics.client.Helper;
import star.genetics.genetic.model.Gene;

import com.google.gwt.json.client.JSONObject;

public class AlleleImpl implements star.genetics.genetic.model.Allele, Serializable
{
	private static final long serialVersionUID = 1L;
	JSONObject data = new JSONObject();

	AlleleImpl(JSONObject data)
	{
		this.data = data;
	}

	public AlleleImpl(String name, Gene gene)
	{
		data.put(NAME, Helper.wrapString(name));
		data.put(GENE, gene.getJSON());
		gene.getGeneTypes().add(this);
	}

	public String getName()
	{
		return Helper.unwrapString(data.get(NAME));
	}

	public Gene getGene()
	{
		return new GeneImpl(data.get(GENE).isObject());
	}

	@Override
	public String toString()
	{
		return getGene().getName() + " " + getName(); //$NON-NLS-1$
	}

	@Override
	public boolean equals(Object obj)
	{
		boolean ret = false;
		if (obj instanceof AlleleImpl)
		{
			AlleleImpl that = (AlleleImpl) obj;
			if (this.getGene() != null && this.getGene().equals(that.getGene()))
			{
				ret = this.getName().equals(that.getName());
			}
		}
		return ret;
	}

	@Override
	public int hashCode()
	{
		return getName().hashCode() ^ getGene().hashCode();
	}

	@Override
	public JSONObject getJSON()
	{
		return data;
	}
}
