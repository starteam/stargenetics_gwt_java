package star.genetics.client;

import star.genetics.genetic.impl.ChromosomeImpl;
import star.genetics.genetic.impl.GeneImpl;
import star.genetics.genetic.impl.GenomeImpl;
import star.genetics.genetic.impl.MatingEngineImpl_XY;
import star.genetics.genetic.impl.ModelImpl;
import star.genetics.genetic.model.GeneticModel;
import star.genetics.genetic.model.MatingEngine;
import star.genetics.shared.FieldVerifier;

import com.google.gwt.core.client.EntryPoint;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Stargenetics_gwt_java implements EntryPoint
{
	/**
	 * The message displayed to the user when the server cannot be reached or returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while " + "attempting to contact the server. Please check your network " + "connection and try again.";

	MatingEngineImpl_XY me = new MatingEngineImpl_XY();

	public void test2()
	{
		Object self = this;
		MatingEngineImpl_XY me = this.me;
		test(self, me);
	}
	
	public static String add(int a,int b)
	{
		return "" + a+b;
	}

	public native void test(Object self, MatingEngine me)
	/*-{
		$wnd.__sg_entry_point = self;
		$wnd.__sg_me = me;
		$wnd.__sg_add = $entry(@star.genetics.client.Stargenetics_gwt_java::add(II));
		console.info( "Done!" );
		return "Not A Number";
	}-*/;

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad()
	{
		MatingEngineImpl_XY xy = new MatingEngineImpl_XY();
		ModelImpl impl = new ModelImpl();
		GenomeImpl genome = new GenomeImpl();
		ChromosomeImpl c = new ChromosomeImpl("name", genome);
		GeneImpl g = new GeneImpl("test", 2, c);
		impl.setGenome(genome);
		impl.setMater(xy);
		System.out.println(impl);
		test(this,xy);
	}
}
