package star.genetics.genetic.impl;

import java.io.Serializable;

import star.genetics.client.Helper;
import star.genetics.genetic.model.CrateSet;
import star.genetics.genetic.model.Creature.Sex;
import star.genetics.genetic.model.GelRules;
import star.genetics.genetic.model.Genome;
import star.genetics.genetic.model.MatingEngine;
import star.genetics.visualizers.VisualizerFactory;

import com.google.gwt.json.client.JSONNull;
import com.google.gwt.json.client.JSONObject;

public class ModelImpl implements star.genetics.genetic.model.ModelWriter, Serializable
{
	private static final long serialVersionUID = 1L;
	
	private JSONObject data;

	public ModelImpl()
	{
		data = new JSONObject();
		data.put(MALERECOMBINATIONRATE, Helper.wrapNumber(1f));
		data.put(FEMALERECOMBINATIONRATE, Helper.wrapNumber(1f));
		data.put(SPONTANIOUSMALES, Helper.wrapNumber(0.001f));
		data.put(MATINGSCOUNT, Helper.wrapNumber(Integer.MAX_VALUE));
		data.put(PROGENIESCOUNT, Helper.wrapNumber(50));
		data.put(CRATESET, new CrateSetImpl().getJSON());
		data.put(RULESET, JSONNull.getInstance());
		data.put(GENOME, JSONNull.getInstance());
	}

	public void setVisualizerClass(String className)
	{
		data.put(visualFactory, Helper.wrapString(className));
	}

	public void setCreatures(star.genetics.genetic.model.CreatureSet creatures)
	{
		data.put(CREATURES, creatures.getJSON());
	}

	public void setRules(star.genetics.genetic.model.RuleSet rules)
	{
		data.put(RULESET, rules.getJSON());

	}

	public star.genetics.genetic.model.RuleSet getRules()
	{
		return new RuleSetImpl(data.get(RULESET).isObject());
	}

	public void setMater(MatingEngineImpl_XY mater)
	{
		data.put( MATER , mater.getJSON());
	}

	public void setRecombinationRate(float rate, Sex sex)
	{
		if (Sex.MALE.equals(sex))
		{
			data.put(MALERECOMBINATIONRATE, Helper.wrapNumber(rate));
		}
		else
		{
			data.put(FEMALERECOMBINATIONRATE, Helper.wrapNumber(rate));
		}
	}

	public void setGenome(Genome genome)
	{
		data.put(GENOME,genome.getJSON());
	}

	public Genome getGenome()
	{
		return new GenomeImpl(data.get(GENOME).isObject());
	}

	public MatingEngine getMatingEngine()
	{
		MatingEngine mater = null;
		if (data.get(MATER).isObject() == null)
		{
			if (Genome.SexType.XY.equals(getGenome().getSexType()))
			{
				float twinning = 0;
				float identical = 0;
				// MatingEngineMetadata md = (MatingEngineMetadata) modelMetadata.get(MatingEngineMetadata.class);
				// if (md != null)
				// {
				// twinning = md.getTwinningFrequency();
				// identical = md.getIdenticalTwinsFrequency();
				// }
				mater = new MatingEngineImpl_XY(maleRecombinationRate(), femaleRecombinationRate(), 0.5f, getProgeniesCount(), twinning, identical);
			}
			else if (Genome.SexType.XO.equals(getGenome().getSexType()))
			{
				mater = new MatingEngineImpl_XO(maleRecombinationRate(), femaleRecombinationRate(), 0.5f, getProgeniesCount(), getSpontaniousMales());
			}
			else if (Genome.SexType.Aa.equals(getGenome().getSexType()))
			{
				mater = new MatingEngineImpl_MAT(maleRecombinationRate(), femaleRecombinationRate(), 0.5f, getProgeniesCount());
			}
			else if (Genome.SexType.UNISEX.equals(getGenome().getSexType()))
			{
				mater = new MatingEngineImpl_UNISEX(femaleRecombinationRate(), getProgeniesCount());
			}
		} else {
			if (Genome.SexType.XY.equals(getGenome().getSexType()))
			{
				mater = new MatingEngineImpl_XY(data.get(MATER).isObject());
			}
		}
		return mater;
	}

	public star.genetics.genetic.model.CreatureSet getCreatures()
	{
		return new CreatureSetImpl(data.get(CREATURES).isObject());
	}

	public float getRecombinationRate(Sex sex)
	{
		return (Sex.MALE.equals(sex)) ? maleRecombinationRate() : femaleRecombinationRate();
	}

	private float maleRecombinationRate()
	{
		return Helper.unwrapNumber(data.get(MALERECOMBINATIONRATE));
	}

	private float femaleRecombinationRate()
	{
		return Helper.unwrapNumber(data.get(FEMALERECOMBINATIONRATE));
	}

	public CrateSet getCrateSet()
	{
		return new CrateSetImpl(data.get(CRATESET).isObject());
	}

	public int getProgeniesCount()
	{
		return Math.round(Helper.unwrapNumber(data.get(PROGENIESCOUNT)));
	}

	public VisualizerFactory getVisualizerFactory()
	{
		return new VisualizerFactoryImpl(Helper.unwrapString(data.get(visualFactory)));
	}

	public void setProgeniesCount(int progeniesCount)
	{
		data.put(PROGENIESCOUNT, Helper.wrapNumber(progeniesCount > 1 ? progeniesCount : 1));
	}

	public void setMatingsCount(int matingsCount)
	{
		data.put(MATINGSCOUNT, Helper.wrapNumber(matingsCount));
	}

	public int getMatingsCount()
	{
		return Math.round(Helper.unwrapNumber(data.get(MATINGSCOUNT)));
	}

	public void setSpontaniousMales(float ratio)
	{
		data.put(SPONTANIOUSMALES, Helper.wrapNumber(ratio));
	}

	public float getSpontaniousMales()
	{
		return Helper.unwrapNumber(data.get(SPONTANIOUSMALES));
	}

	public void setGelRules(GelRules gri)
	{
		data.put(GELRULESET, gri.getJSON());
	}

	public GelRules getGelRules()
	{
		return new GelRulesImpl(data.get(GELRULESET).isObject());
	}

	public JSONObject getJSON()
	{
		return data;
	}
}
