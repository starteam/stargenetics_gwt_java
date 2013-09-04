package star.genetics.parser;

import java.util.HashMap;
import java.util.Map;

import star.genetics.genetic.impl.CreatureImpl;
import star.genetics.genetic.impl.CreatureSetImpl;
import star.genetics.genetic.impl.DiploidAllelesImpl;
import star.genetics.genetic.impl.GeneticMakeupImpl;
import star.genetics.genetic.impl.ModelImpl;
import star.genetics.genetic.model.Creature.Sex;
import star.genetics.genetic.model.Allele;
import star.genetics.genetic.model.Gene;
import star.genetics.genetic.model.Genome;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;

public class StrainsParser
{

	public static void parse(ModelImpl model, JSONObject strains)
    {
		CreatureSetImpl set = new CreatureSetImpl();
		parseStrains( model ,  set, strains.get("initial").isObject() );
		model.setCreatures(set);
    }

	private static void parseStrains(ModelImpl model, CreatureSetImpl set, JSONObject strains)
    {
		JSONArray list = strains.get("list").isArray();
		for( int i = 0 ; i < list.size() ; i++ )
		{
			parseStrain( model, set , list.get(i).isObject());
		}
    }

	private static void parseStrain(ModelImpl model, CreatureSetImpl set, JSONObject strain)
    {
		String name = strain.get("name").isString().stringValue();
		String ssex = strain.get("sex").isString().stringValue();
		Sex sex = "M".equalsIgnoreCase(ssex) ? Sex.MALE : Sex.FEMALE;
		GeneticMakeupImpl makeup = parseGeneticMakeup(model, strain.get("alleles").isArray());
		Map<String, String> properties =new HashMap<String, String>();
		CreatureImpl c = new CreatureImpl(name, model.getGenome(), sex, makeup, model.getMatingsCount(), properties, null);
		set.add(c);
    }

	private static GeneticMakeupImpl parseGeneticMakeup(ModelImpl model, JSONArray array)
    {
	    GeneticMakeupImpl ret = new GeneticMakeupImpl();
	    for( int i = 0 ; i < array.size() ; i++ )
	    {
	    	parseAlleles( model , ret, array.get(i).isString().stringValue() );
	    }
		return ret;
    }

	private static void parseAlleles(ModelImpl model, GeneticMakeupImpl ret, String string)
    {
		Genome genome = model.getGenome();
		System.out.println( "Allele string " + string);
		String[] alleleStr = string.split(",");
		Allele[] alleles = new Allele[alleleStr.length];
		for( int i = 0 ; i < alleleStr.length ; i++ )
		{
			alleles[i] = getAllele(genome, alleleStr[i]);
		}
		ret.put( alleles[0].getGene() , new DiploidAllelesImpl(alleles));
    }

	private static Allele getAllele(star.genetics.genetic.model.Genome g, String allele)
	{
		Allele ret = null;
		for (Gene gene : g.getGenes())
		{
			ret = gene.getAlleleByName(allele.trim());
			if (ret != null)
			{
				break;
			}
		}
		return ret;

	}

}
