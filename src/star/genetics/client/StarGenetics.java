package star.genetics.client;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;

import star.genetics.genetic.model.Creature;
import star.genetics.genetic.model.CreatureSet;
import star.genetics.genetic.model.Model;
import star.genetics.parser.ModelParser;

public class StarGenetics
{
	enum Commands
	{
		Open, ListStrains;

		public boolean is(String command)
		{
			return this.toString().equalsIgnoreCase(command);
		}
	}

	private static Logger logger = Logger.getLogger("StarGenetics");
	private Model model;

	private void setModel(Model model)
	{
		this.model = model;
	}

	private Model getModel()
	{
		return model;
	}

	public void execute(Exec exec)
	{
		try
		{
			logger.log(Level.FINE, "in Execute.");
			String command = exec.getCommand();
			System.out.println("command " + command);
			if (Commands.Open.is(command))
			{
				open(exec);
			}
			else if (Commands.ListStrains.is(command))
			{
				listStrains(exec);
			}
		}
		catch (Throwable t)
		{
			t.printStackTrace();
			JSONObject ret = new JSONObject();
			ret.put("error", new JSONString(t.getMessage()));
			exec.onError(ret.getJavaScriptObject());

		}
	}

	private void open(Exec exec)
	{
		Open open = exec.cast();
		if (Open.VERSION_1.equalsIgnoreCase(open.getProtocol()))
		{
			setModel(ModelParser.parse(open.getJSONModel()));
			JSONObject ret = new JSONObject();
			exec.onSuccess(ret.getJavaScriptObject());
		}
		else
		{
			JSONObject ret = new JSONObject();
			ret.put("error", new JSONString("Unrecognized protocol."));
			exec.onError(ret.getJavaScriptObject());
		}
	}

	private void listStrains(Exec exec)
	{
		JSONObject ret = new JSONObject();
		JSONArray retset = new JSONArray();
		ret.put("strains", retset);
		Model model = getModel();
		CreatureSet strains = model.getCreatures();
		for (int i = 0; i < strains.size(); i++)
		{
//			retset.set(i, StrainHelper.getShort(strains.get(i)));
			retset.set(i, StrainHelper.getLong(strains.get(i),model.getRules()));
		}
		exec.onSuccess(ret.getJavaScriptObject());
	}

}