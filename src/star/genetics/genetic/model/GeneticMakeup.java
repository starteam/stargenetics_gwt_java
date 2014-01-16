package star.genetics.genetic.model;

import star.genetics.client.JSONable;

public interface GeneticMakeup extends JSONable
{

	DiploidAlleles get(Gene g);

	void put(Gene g, DiploidAlleles d);

	boolean test(Chromosome c, java.util.Map<Gene, DiploidAlleles> map);

}
