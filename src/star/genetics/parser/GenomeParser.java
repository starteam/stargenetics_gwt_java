package star.genetics.parser;

import java.util.logging.Level;
import java.util.logging.Logger;

import star.genetics.genetic.impl.AlleleImpl;
import star.genetics.genetic.impl.ChromosomeImpl;
import star.genetics.genetic.impl.GeneImpl;
import star.genetics.genetic.impl.GenomeImpl;
import star.genetics.genetic.impl.ModelImpl;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;

public class GenomeParser
{
	static Logger logger = Logger.getLogger("GenomeParser");

	public static void parse(ModelImpl model, JSONObject genome)
	{
		logger.log(Level.INFO, "step 1");
		GenomeImpl ret = new GenomeImpl(model);
		logger.log(Level.INFO, "step 2");
		model.setGenome(ret);
		logger.log(Level.INFO, "step 3");
		parseChromosomes(ret, genome.get("chromosomes").isObject(), model);
		logger.log(Level.INFO, "step 4");

	}

	private static void parseChromosomes(GenomeImpl genome, JSONObject chromosomes, ModelImpl model)
	{
		logger.log(Level.INFO, "step 5");
		for (String chromosome_id : chromosomes.keySet())
		{
			logger.log(Level.INFO, "step 7");
			ChromosomeImpl c = new ChromosomeImpl(chromosome_id, genome, model);
			logger.log(Level.INFO, "step 8");
			parseChromosome(c, chromosomes.get(chromosome_id).isObject(), model);
			logger.log(Level.INFO, "step 9");
		}
		logger.log(Level.INFO, "step 6");
	}

	private static void parseChromosome(ChromosomeImpl c, JSONObject chromosome, ModelImpl model)
	{
		logger.log(Level.INFO, "step 10");
		String name = chromosome.get("name").isString().stringValue();
		logger.log(Level.INFO, "step 11");
		parseGenes(c, chromosome.get("genes").isArray(), model);
		logger.log(Level.INFO, "step 12");
	}

	private static void parseGenes(ChromosomeImpl c, JSONArray genes, ModelImpl model)
	{
		logger.log(Level.INFO, "step 13");
		for (int i = 0; i < genes.size(); i++)
		{
			logger.log(Level.INFO, "step 14");
			JSONObject gene = genes.get(i).isObject();
			logger.log(Level.INFO, "step 15");
			parseGene(c, gene, model);
			logger.log(Level.INFO, "step 16");
		}
		logger.log(Level.INFO, "step 17");

	}

	private static void parseGene(ChromosomeImpl c, JSONObject gene, ModelImpl model)
	{
		logger.log(Level.INFO, "step 18");
		String name = gene.get("name").isString().stringValue();
		logger.log(Level.INFO, "step 19");
		float position = (float) gene.get("position").isNumber().doubleValue();
		logger.log(Level.INFO, "step 20");
		GeneImpl g = new GeneImpl(name, position, c, model);
		logger.log(Level.INFO, "step 21");
		parseAlleles(g, gene.get("alleles").isArray(), model);
		logger.log(Level.INFO, "step 22");
	}

	private static void parseAlleles(GeneImpl gene, JSONArray alleles, ModelImpl model)
	{
		logger.log(Level.INFO, "step 23");

		for (int i = 0; i < alleles.size(); i++)
		{
			logger.log(Level.INFO, "step 24");

			parseAllele(gene, alleles.get(i).isObject(), model);
			logger.log(Level.INFO, "step 25");

		}
		logger.log(Level.INFO, "step 26");

	}

	private static void parseAllele(GeneImpl gene, JSONObject allele, ModelImpl model)
	{
		logger.log(Level.INFO, "step 27");
		String name = allele.get("name").isString().stringValue();
		logger.log(Level.INFO, "step 28");
		AlleleImpl a = new AlleleImpl(name, gene, model);
		logger.log(Level.INFO, "step 29");
	}

}
