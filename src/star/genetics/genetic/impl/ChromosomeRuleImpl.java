package star.genetics.genetic.impl;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

import com.google.gwt.json.client.JSONObject;

import star.genetics.genetic.model.Allele;
import star.genetics.genetic.model.Chromosome;
import star.genetics.genetic.model.Creature;
import star.genetics.genetic.model.DiploidAlleles;
import star.genetics.genetic.model.Gene;
import star.genetics.genetic.model.GeneticMakeup;

class ChromosomeRuleImpl implements Serializable, IndividualRule
{
	private static final long serialVersionUID = 1L;

	private final Chromosome chromosome()
	{
		return new ChromosomeImpl(data.get(CHROMOSOME).isObject());
	};

	private JSONObject data;

	ChromosomeRuleImpl(JSONObject data)
	{
		this.data = data;
	}

	ChromosomeRuleImpl(Chromosome c)
	{
		data = new JSONObject();
		data.put(CHROMOSOME, c.getJSON());
		data.put("MAKEUP", new JSONObject());
	}

	public JSONObject getJSON()
	{
		return data;
	};

	RuleMakeup get()
	{
		return new RuleMakeup(data);
	}

	public boolean test(GeneticMakeup makeup, Creature.Sex sex)
	{
		return makeup.test(chromosome(), get());
	}

	void addAllele(int strand, Allele a)
	{
		if (strand == 0)
		{
			get().put(a.getGene(), new DiploidAllelesImpl(a, null));
		}
		else
		{
			DiploidAlleles diploid = get().get(a.getGene());
			get().put(a.getGene(), new DiploidAllelesImpl(diploid != null ? diploid.get(0) : null, a));
		}

	}

	private Chromosome getChromosome()
	{
		return chromosome();
	}

}
