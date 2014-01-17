package star.genetics.genetic.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import star.genetics.beans.StringTokenizer;
import star.genetics.client.Helper;
import star.genetics.client.JSONableList;
import star.genetics.client.JSONableMap;
import star.genetics.client.MessageFormat;
import star.genetics.client.Messages;
import star.genetics.genetic.model.Allele;
import star.genetics.genetic.model.Chromosome;
import star.genetics.genetic.model.Creature;
import star.genetics.genetic.model.Gene;
import star.genetics.genetic.model.GeneticMakeup;
import star.genetics.genetic.model.Genome;
import star.genetics.genetic.model.Genome.SexType;
import star.genetics.genetic.model.Model;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;

public class RuleImpl implements star.genetics.genetic.model.Rule, Serializable
{
	private static final long serialVersionUID = 1L;

	private JSONableList<IndividualRule> compiledRules()
	{
		return new JSONableList<IndividualRule>(data.get(COMPILEDRULES).isArray())
		{

			@Override
			public IndividualRule create(JSONObject data)
			{
				String kind = Helper.unwrapString(data.get(KIND));
				if ("ChromosomeRuleImpl".equals(kind))
				{
					return new ChromosomeRuleImpl(data, getModel());
				}
				else if ("HaploidRuleImpl".equals(kind))
				{
					return new HaploidRuleImpl(data);
				}
				else if ("SexRuleImpl".equals(kind))
				{
					return new SexRuleImpl(data, getModel());
				}
				else
				{
					throw new RuntimeException("Missing rule kind");
				}
			}

			@Override
			public void add(IndividualRule element)
			{
				JSONObject data = element.getJSON();
				if (element instanceof ChromosomeRuleImpl)
				{
					data.put(KIND, Helper.wrapString("ChromosomeRuleImpl"));
				}
				else if (element instanceof HaploidRuleImpl)
				{
					data.put(KIND, Helper.wrapString("HaploidRuleImpl"));

				}
				else if (element instanceof SexRuleImpl)
				{
					data.put(KIND, Helper.wrapString("SexRuleImpl"));
				}
				else
				{
					throw new RuntimeException("Unknown rule type");
				}
				super.add(element);
			}
		};
	}

	private final JSONObject data;
	private final Model model;

	public Model getModel()
	{
		return model;
	}

	public RuleImpl(JSONObject data, Model model)
	{
		this.data = data;
		this.model = model;
	}

	static Logger logger = Logger.getLogger("RuleImpl");

	public RuleImpl(String rule, HashMap<String, String> properties, Genome g, Model model)
	{
		this.model = model;
		logger.log(Level.INFO, "step 1");
		data = new JSONObject();
		logger.log(Level.INFO, "step 2");
		data.put(NAME, Helper.wrapString(rule));
		logger.log(Level.INFO, "step 3");
		data.put(COMPILEDRULES, new JSONArray());
		logger.log(Level.INFO, "step 4");
		data.put(PROPERTIES, new JSONObject());
		addProperties(properties);
		logger.log(Level.INFO, "step 5");
		if (!isDefault())
		{
			logger.log(Level.INFO, "step 5a");
			parseRules(rule, g);
		}
		logger.log(Level.INFO, "step 6");
	}

	@Override
	public JSONObject getJSON()
	{
		return data;
	}

	public void addProperties(Map<String, String> properties)
	{
		for (Entry<String, String> entry : properties.entrySet())
		{
			getProperties().put(entry.getKey(), entry.getValue());
		}
	}

	public JSONableMap getProperties()
	{
		return new JSONableMap(data.get(PROPERTIES).isObject());
	}

	String getName()
	{
		logger.log(Level.INFO, "step 5c" + data);
		return Helper.unwrapString(data.get(NAME));
	}

	private void parseRules(String rule, Genome g)
	{
		logger.log(Level.INFO, "pR 1");

		data.put(COMPILEDRULES, new JSONArray());
		logger.log(Level.INFO, "pR 2");
		StringTokenizer ruleSplit = new StringTokenizer(rule, ";"); //$NON-NLS-1$
		logger.log(Level.INFO, "pR 3");
		while (ruleSplit.hasMoreTokens())
		{
			logger.log(Level.INFO, "pR 4");
			String oneRule = ruleSplit.nextToken().trim();
			boolean isRuleParsed = false;
			logger.log(Level.INFO, "pR 5");

			if (!isRuleParsed)
			{
				logger.log(Level.INFO, "pR 6");

				isRuleParsed = parseSexRule(oneRule, g);
			}
			if (!isRuleParsed)
			{
				logger.log(Level.INFO, "pR 7");

				isRuleParsed = parseHaploidRule(oneRule, g);
			}
			if (!isRuleParsed)
			{
				logger.log(Level.INFO, "pR 8");

				parseOneRule(oneRule, g);

			}
			logger.log(Level.INFO, "pR 9");

		}
		logger.log(Level.INFO, "pR 10");

	}

	private boolean parseHaploidRule(String oneRule, Genome g)
	{
		boolean ret = false;
		String s = oneRule.trim().toLowerCase();
		if (SexType.Aa.equals(g.getSexType()))
		{
			if (s.startsWith("haploid")) //$NON-NLS-1$
			{
				ret = true;
				compiledRules().add(new HaploidRuleImpl());
			}
		}
		return ret;

	}

	private boolean parseSexRule(String oneRule, Genome g)
	{
		boolean ret = false;
		String s = oneRule.trim().toLowerCase();
		if (SexType.XY.equals(g.getSexType()) || SexType.XO.equals(g.getSexType()))
		{
			if (s.startsWith("sex:")) //$NON-NLS-1$
			{
				s = s.replace(" ", ""); //$NON-NLS-1$ //$NON-NLS-2$
				if (s.equals("sex:male")) //$NON-NLS-1$
				{
					ret = true;
					compiledRules().add(new SexRuleImpl(Creature.Sex.MALE, getModel()));
				}
				else if (s.equals("sex:female")) //$NON-NLS-1$
				{
					ret = true;
					compiledRules().add(new SexRuleImpl(Creature.Sex.FEMALE, getModel()));
				}
			}
		}
		else if (SexType.Aa.equals(g.getSexType()))
		{
			if (s.startsWith("sex:")) //$NON-NLS-1$
			{
				s = s.replace(" ", ""); //$NON-NLS-1$ //$NON-NLS-2$
				if (s.equals("sex:mata")) //$NON-NLS-1$
				{
					ret = true;
					compiledRules().add(new SexRuleImpl(Creature.Sex.MALE, getModel()));
				}
				else if (s.equals("sex:matalpha")) //$NON-NLS-1$
				{
					ret = true;
					compiledRules().add(new SexRuleImpl(Creature.Sex.FEMALE, getModel()));
				}
			}
		}
		return ret;
	}

	private void parseOneRule(String oneRule, Genome g)
	{
		logger.log(Level.INFO, "pOR 1");
		int chromosomeSplit = oneRule.indexOf(':');
		String chromosomeName = null;
		Chromosome chromosome = null;

		if (chromosomeSplit != -1)
		{
			logger.log(Level.INFO, "pOR 2");
			chromosomeName = oneRule.substring(0, chromosomeSplit).trim();
			chromosome = g.getChromosomeByName(chromosomeName);
		}
		else
		{
			logger.log(Level.INFO, "pOR 3");
			int indexC = oneRule.indexOf(',');
			int indexS = oneRule.indexOf(' ');
			if (indexC == -1)
			{
				indexC = oneRule.length();
			}
			if (indexS == -1)
			{
				indexS = oneRule.length();
			}
			int index = Math.min(indexC, indexS);
			String alleleName = oneRule.substring(0, index);
			for (Gene gene : g.getGenes())
			{
				if (gene.getAlleleByName(alleleName) != null)
				{
					chromosome = gene.getChromosome();
					chromosomeName = chromosome.getName();
					break;
				}
			}
		}
		logger.log(Level.INFO, "pOR 4");

		if (chromosome != null)
		{
			logger.log(Level.INFO, "pOR 5");

			String alleles = oneRule.substring(chromosomeSplit + 1).trim();
			logger.log(Level.INFO, "pOR 9");
			ChromosomeRuleImpl cr = makeChromosomeRule(alleles, chromosome);
			logger.log(Level.INFO, "pOR 10");
			if (cr != null)
			{
				logger.log(Level.INFO, "pOR 11");

				compiledRules().add(cr);
				logger.log(Level.INFO, "pOR 12");

			}
			logger.log(Level.INFO, "pOR 8");

		}
		else
		{
			logger.log(Level.INFO, "pOR 6");

			if (!"default".equalsIgnoreCase(oneRule.trim())) //$NON-NLS-1$
			{
				throw new RuntimeException(MessageFormat.format(Messages.getString("RuleImpl.2"), oneRule)); //$NON-NLS-1$
			}
		}
		logger.log(Level.INFO, "pOR 7");

	}

	private ChromosomeRuleImpl makeChromosomeRule(String alleles, Chromosome chromosome)
	{
		logger.log(Level.INFO, "mcR 1");
		ChromosomeRuleImpl cr = new ChromosomeRuleImpl(chromosome, getModel());
		logger.log(Level.INFO, "mcR 2");
		StringTokenizer strandSplit = new StringTokenizer(alleles, ","); //$NON-NLS-1$
		boolean add = false;
		int strand = 0;
		logger.log(Level.INFO, "mcR 3");
		while (strandSplit.hasMoreTokens())
		{
			logger.log(Level.INFO, "mcR 4");
			String strandRule = strandSplit.nextToken().trim();
			StringTokenizer allelesSplit = new StringTokenizer(strandRule, " "); //$NON-NLS-1$
			logger.log(Level.INFO, "mcR 5");
			while (allelesSplit.hasMoreTokens())
			{
				logger.log(Level.INFO, "mcR 6");
				String alleleName = allelesSplit.nextToken().trim();
				logger.log(Level.INFO, "mcR 7");
				Allele allele = chromosome.getAlleleByName(alleleName);
				logger.log(Level.INFO, "mcR 8");
				if (allele != null)
				{
					logger.log(Level.INFO, "mcR 8 a");

					cr.addAllele(strand, allele);
					logger.log(Level.INFO, "mcR 8 b");
					add = true;
				}
				else
				{
					logger.log(Level.INFO, "mcR 8 exc");

					throw new RuntimeException(MessageFormat.format(Messages.getString("RuleImpl.1"), alleleName, getName())); //$NON-NLS-1$
				}
				logger.log(Level.INFO, "mcR 9");
			}
			strand++;
			logger.log(Level.INFO, "mcR 10");
		}
		logger.log(Level.INFO, "mcR 11");
		return add ? cr : null;
	}

	public boolean isDefault()
	{
		logger.log(Level.INFO, "step 5b");
		return DEFAULT.equalsIgnoreCase(getName()) || "*".equalsIgnoreCase(getName());
	}

	public boolean isMatching(GeneticMakeup makeup, Creature.Sex sex)
	{
		boolean ret = compiledRules().size() > 0;
		for (IndividualRule c : compiledRules())
		{
			ret &= c.test(makeup, sex);
		}
		return ret;
	}

}
