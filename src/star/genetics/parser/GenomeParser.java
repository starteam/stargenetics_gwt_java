package star.genetics.parser;

import star.genetics.genetic.impl.AlleleImpl;
import star.genetics.genetic.impl.ChromosomeImpl;
import star.genetics.genetic.impl.GeneImpl;
import star.genetics.genetic.impl.GenomeImpl;
import star.genetics.genetic.impl.ModelImpl;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;

public class GenomeParser
{

	public static void parse(ModelImpl model, JSONObject genome)
    {
		GenomeImpl ret = new GenomeImpl();
		parseChromosomes( ret , genome.get("chromosomes").isObject());
		model.setGenome(ret);
    }


	private static void parseChromosomes(GenomeImpl genome, JSONObject chromosomes)
    {
	    for( String chromosome_id : chromosomes.keySet() ) {
	    	ChromosomeImpl c = new ChromosomeImpl(chromosome_id, genome);
	    	parseChromosome( c, chromosomes.get(chromosome_id).isObject() );
	    }
    }


	private static void parseChromosome(ChromosomeImpl c, JSONObject chromosome)
    {
		String name = chromosome.get("name").isString().toString();
		parseGenes( c , chromosome.get("genes").isArray());
    }


	private static void parseGenes(ChromosomeImpl c, JSONArray genes)
    {
		for( int i = 0 ; i < genes.size() ; i++)
		{
			JSONObject gene = genes.get(i).isObject();
			parseGene( c , gene );
		}
	    
    }


	private static void parseGene(ChromosomeImpl c, JSONObject gene)
    {
		String name = gene.get("name").isString().toString();
		float position = (float)gene.get("position").isNumber().doubleValue();
		GeneImpl g = new GeneImpl(name, position, c);
		parseAlleles( g , gene.get("alleles").isArray());
    }


	private static void parseAlleles(GeneImpl gene, JSONArray alleles)
    {
		for( int i = 0 ; i < alleles.size() ; i++ )
		{
			parseAllele( gene , alleles.get(i).isObject());
		}
    }


	private static void parseAllele(GeneImpl gene, JSONObject allele)
    {
		String name = allele.get("name").isString().toString();
		AlleleImpl a = new AlleleImpl(name, gene);
    }


}
