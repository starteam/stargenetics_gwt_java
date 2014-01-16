package star.genetics.client;

import com.google.gwt.json.client.JSONObject;

public interface JSONable
{
	static final String NAME = "name";

	static final String POSITON = "position";

	static final String CHROMOSOME = "chromosome";
	static final String CHROMOSOMES = "chromosomes";
	static final String GENE = "gene";
	static final String GENES = "genes";
	static final String GENETYPES = "genetypes";
	static final String ALLELE_1 = "allele_1";
	static final String ALLELE_2 = "allele_2";
	static final String visualFactory = "visualFactory";
	static final String CREATURES = "creatures";
	static final String ID = "id";
	static final String SET = "set";
	static final String GENOME = "genome";
	static final String MAKEUP = "makeup";
	static final String SEX = "sex";
	static final String MATINGSAVAILABLE = "matings_available";
	static final String PROPERTIES = "properties";
	static final String PARENTS = "parents";
	static final String UUID = "uuid";
	static final String READONLY = "readonly";
	static final String PROGENIES = "progenies";

	JSONObject getJSON();
}
