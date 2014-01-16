package star.genetics.genetic.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import star.genetics.client.JSONableList;
import star.genetics.genetic.model.Chromosome;
import star.genetics.genetic.model.Gene;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;

public class GenomeImpl implements star.genetics.genetic.model.Genome, Serializable
{
	private static final long serialVersionUID = 1L;
	private String name;
	private JSONObject data;

	public GenomeImpl(JSONObject data)
	{
		this.data = data;
	}

	public GenomeImpl()
	{
		this.data = new JSONObject();
		data.put(SEX, SexType.XY.getJSON());
		data.put(CHROMOSOMES, new JSONArray());
	}

	/**
	 * @param organismName
	 *            the organismName to set
	 */
	public void setSexType(String sexTypeName)
	{
		data.put(SEX, SexType.parse(sexTypeName).getJSON());
	}

	public SexType getSexType()
	{
		return SexType.fromJSON(data.get(SEX));
	}

	public Chromosome getChromosomeByName(String name)
	{
		Chromosome ret = null;
		for (Chromosome x : getChromosomes())
		{
			if (name.equals(x.getName()))
			{
				ret = x;
				break;
			}
		}
		return ret;
	}

	public void addChromosome(Chromosome c)
	{
		getChromosomes().add(c);
	}

	public Iterator<Chromosome> iterator()
	{
		return getChromosomes().iterator();
	}

	JSONableList<Chromosome> getChromosomes()
	{
		return new JSONableList<Chromosome>(data.get(CHROMOSOMES).isArray())
		{
			@Override
			public Chromosome create(JSONObject data)
			{
				return new ChromosomeImpl(data);
			}
		};
	}

	public List<Gene> getGenes()
	{
		ArrayList<Gene> genes = new ArrayList<Gene>();
		for (star.genetics.genetic.model.Chromosome c : getChromosomes())
		{
			for (Gene g : c.getGenes())
			{
				genes.add(g);
			}
		}
		return genes;
	}

	@Override
	public JSONObject getJSON()
	{
		return data;
	}
}
