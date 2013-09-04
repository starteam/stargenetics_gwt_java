package star.genetics.parser;

import com.google.gwt.json.client.JSONObject;

import star.genetics.genetic.impl.ModelImpl;

public class VisualizerParser
{
	public static void parse(ModelImpl model, JSONObject visualizer)
    {
		String visualizer_name = visualizer.get("name").isString().stringValue();
		if("fly".equalsIgnoreCase(visualizer_name))
		{
			model.setVisualizerClass("star.genetics.visualizers.Fly");
		}	    
    }

}
