package star.genetics.client;

import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import star.genetics.client.messages.Exec;
import star.genetics.genetic.impl.ChromosomeImpl;
import star.genetics.genetic.impl.GeneImpl;
import star.genetics.genetic.impl.GenomeImpl;
import star.genetics.genetic.impl.MatingEngineImpl_XY;
import star.genetics.genetic.impl.ModelImpl;
import star.genetics.genetic.model.GeneticModel;
import star.genetics.genetic.model.MatingEngine;
import star.genetics.shared.FieldVerifier;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;

public class Stargenetics_gwt_java implements EntryPoint
{	
	private static Logger logger = Logger.getLogger("StarGenetics");
	private static HashMap<String, StarGenetics> map = new HashMap<String, StarGenetics>();

	

	public void onModuleLoad()
	{
		setupInterface();
	}
	
	private static native void setupInterface()
	/*-{
		$wnd.__sg_bg_exec = $entry(@star.genetics.client.Stargenetics_gwt_java::execute(*));
		if( typeof(console) == 'object' && console && console.info ) {
			console.info( "setup interface" ) ;
		}
	}-*/;

	private static native void log( String str )
	/*-{
		if( typeof(console) == 'object' && console && console.info )
		{
			console.info( str ) ;
		}
	}-*/;
	public static void execute(Exec obj)
	{	
		log( "execute start " );
		
		if( obj != null )
		{
			String token = obj.getToken();
			log( "execute start token:" + token );
			if( token != null ) {
				if(! map.containsKey( token ) )
				{
					logger.log( Level.INFO, "New StarGenetics backend for token " + token );
					map.put(token, new StarGenetics());
				}
				else
				{
					logger.log( Level.INFO, "Loading existing StarGenetics backend for token " + token );
				}
				map.get(token).execute(obj);
			}
			else
			{
				logger.log( Level.WARNING , "Token not available, bailing out.");
			}
		}
		else
		{
			logger.log( Level.WARNING , "Input argument invalid.");			
		}
		log( "execute done " );

	}


}
