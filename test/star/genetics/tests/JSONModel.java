package star.genetics.tests;

import star.genetics.client.Exec;
import star.genetics.client.Stargenetics_gwt_java;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.junit.client.GWTTestCase;

import static org.junit.Assert.*;

public class JSONModel  extends GWTTestCase
{

	String getJson()
	{
		// getJSON("setupmodel.json");
		return "{\"genetics\":{\"visualizer\":{\"name\":\"fly\"},\"model_metadata\":{},\"experiments\":{},\"engine\":{\"sex_type\":\"XY\",\"male_recombination_rate\":1,\"female_sex_ratio\":0.51,\"avg_offspring_count\":50,\"female_recombination_rate\":1,\"twinning\":0,\"identical_twins_frequency\":0},\"strains\":{\"initial\":{\"name\":\"Initial Strains\",\"list\":[{\"sex\":\"M\",\"alleles\":[\"A,A\",\"B,B\"],\"name\":\"Wildtype M\"},{\"sex\":\"F\",\"alleles\":[\"A,A\",\"B,B\"],\"name\":\"Wildtype F\"},{\"sex\":\"M\",\"alleles\":[\"a,a\",\"b,b\"],\"name\":\"Double Mutant M\"},{\"sex\":\"F\",\"alleles\":[\"a,a\",\"b,b\"],\"name\":\"Double Mutant F\"}]}},\"gel_rules\":{},\"phenotype_rules\":[{\"matches\":\"*\",\"name\":\"default\",\"phenotype\":{\"wings\":1,\"eyes\":\"red\"}},{\"matches\":\"aa\",\"name\":\"white eyes\",\"phenotype\":{\"eyes\":\"white\"}},{\"matches\":\"bb\",\"name\":\"wingless\",\"phenotype\":{\"wings\":0}}],\"genome\":{\"chromosomes\":{\"C_1\":{\"genes\":[{\"position\":25,\"alleles\":[{\"name\":\"A\"},{\"name\":\"a\"}],\"name\":\"red_eyes\"},{\"position\":40,\"alleles\":[{\"name\":\"B\"},{\"name\":\"b\"}],\"name\":\"wingless\"}],\"name\":\"Chromosome 1\"}}}}}\n";
	}

	public void test() 
	{
		open();
	}

	private void open()
    {
		JSONObject data = new JSONObject();
		data.put( "protocol" , new JSONString("Version_1"));
		data.put( "model", JSONParser.parseLenient(getJson()));
		
		JSONObject request = new JSONObject();
		request.put( "token" , new JSONString("1") ) ;
		request.put( "command" , new JSONString("open") ) ;
		request.put( "data" , data ) ;
				
		Exec exec = request.getJavaScriptObject().cast();
		Stargenetics_gwt_java.execute(exec);	    
    }

	@Override
	public String getModuleName()
	{
		return "star.genetics.StarGenetics_gwt_java";
	}
}
