package star.genetics.tests;

import static org.junit.Assert.*;

import org.json.JSONException;
import org.json.JSONObject;

import star.genetics.genetic.model.Model;
import star.genetics.parser.ModelParser;

import com.google.gwt.junit.client.GWTTestCase;

public class SetupModel extends GWTTestCase
{
	String getJson()
	{
		// getJSON("setupmodel.json");
		return "{\"genetics\":{\"visualizer\":{\"name\":\"fly\"},\"model_metadata\":{},\"experiments\":{},\"engine\":{\"sex_type\":\"XY\",\"male_recombination_rate\":1,\"female_sex_ratio\":0.51,\"avg_offspring_count\":50,\"female_recombination_rate\":1,\"twinning\":0,\"identical_twins_frequency\":0},\"gel_rules\":{},\"phenotype_rules\":[{\"matches\":\"*\",\"name\":\"default\",\"phenotype\":{\"wings\":1,\"eyes\":\"red\"}},{\"matches\":\"aa\",\"name\":\"white eyes\",\"phenotype\":{\"eyes\":\"white\"}},{\"matches\":\"bb\",\"name\":\"wingless\",\"phenotype\":{\"wings\":0}}],\"genome\":{\"chromosomes\":{\"C_1\":{\"genes\":[{\"position\":25,\"alleles\":[{\"name\":\"A\"},{\"name\":\"a\"}],\"name\":\"red_eyes\"},{\"position\":40,\"alleles\":[{\"name\":\"B\"},{\"name\":\"b\"}],\"name\":\"wingless\"}],\"name\":\"Chromosome 1\"}}}}}\n";
	}

	public void test()
	{
		Model m = ModelParser.parse(getJson());
		System.out.println( m.getGenome() );
		assertNotNull(m.getGenome());
		assertNotNull(m.getMatingEngine());
		assertNotNull(m.getRules());

	}

	@Override
	public String getModuleName()
	{
		return "star.genetics.StarGenetics_gwt_java";
	}

}
