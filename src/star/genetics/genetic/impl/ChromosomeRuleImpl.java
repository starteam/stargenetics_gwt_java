package star.genetics.genetic.impl;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import star.genetics.client.Helper;
import star.genetics.genetic.model.Allele;
import star.genetics.genetic.model.Chromosome;
import star.genetics.genetic.model.Creature;
import star.genetics.genetic.model.DiploidAlleles;
import star.genetics.genetic.model.Gene;
import star.genetics.genetic.model.GeneticMakeup;
import star.genetics.genetic.model.Model;

import com.google.gwt.json.client.JSONObject;

class ChromosomeRuleImpl implements Serializable, IndividualRule
{
	private static final long serialVersionUID = 1L;

	Model model ;
	public Model getModel()
    {
	    return model;
    }
	
	private final Chromosome chromosome()
	{
		return getModel().getGenome().getChromosomeByName(Helper.unwrapString(data.get(CHROMOSOME)));
	};

	private JSONObject data;

	ChromosomeRuleImpl(JSONObject data, Model model)
	{
		this.data = data;
		this.model = model;
	}

	ChromosomeRuleImpl(Chromosome c, Model model)
	{
		data = new JSONObject();
		data.put(CHROMOSOME, Helper.wrapString(c.getName()));
		data.put(MAKEUP, new JSONObject());
		this.model = model;
	}

	public JSONObject getJSON()
	{
		return data;
	};

	RuleMakeup get()
	{
		return new RuleMakeup(data,getModel());
	}

	public boolean test(GeneticMakeup makeup, Creature.Sex sex)
	{
		return makeup.test(chromosome(), get());
	}

	static Logger logger = Logger.getLogger("ChromosomeRuleImpl");

	void addAllele(int strand, Allele a)
	{
		logger.log(Level.INFO, "a 1");
		if (strand == 0)
		{
			logger.log(Level.INFO, "a 2");
			logger.log(Level.INFO, "a 2a: " + get());
			logger.log(Level.INFO, "a 2d");
			DiploidAlleles da = new DiploidAllelesImpl(a, null,getModel());
			Gene g = a.getGene();
			get().put(g, da);
			logger.log(Level.INFO, "a 3");

		}
		else
		{
			logger.log(Level.INFO, "a 4");
			logger.log(Level.INFO, "a 4a" + get() );
			logger.log(Level.INFO, "a 4b" + a.getGene() );
			DiploidAlleles diploid = get().get(a.getGene());
			logger.log(Level.INFO, "a 5");
			get().put(a.getGene(), new DiploidAllelesImpl(diploid != null ? diploid.get(0) : null, a, getModel()));
			logger.log(Level.INFO, "a 6");
		}
		logger.log(Level.INFO, "a E");

	}

}
