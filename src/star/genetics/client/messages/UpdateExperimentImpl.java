package star.genetics.client.messages;

import star.genetics.client.StrainHelper;
import star.genetics.genetic.impl.MatingException;
import star.genetics.genetic.model.CrateModel;
import star.genetics.genetic.model.Creature;
import star.genetics.genetic.model.CreatureSet;
import star.genetics.genetic.model.Model;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

public class UpdateExperimentImpl
{
	private UpdateExperiment exp;
	
	public UpdateExperimentImpl(UpdateExperiment e)
	{
		this.exp = e ;
	}
	
	public final void execute(Model model)
	{		
		Experiment experiment = exp.getExperiment();
		String id = experiment.getId();
		if (id == null)
		{
			CrateModel cratemodel = model.getCrateSet().newCrateModel();
			if ("mate".equalsIgnoreCase(exp.getExperimentCommand()))
			{
				cratemodel.setName(experiment.getName());
				for (JavaScriptObject so : experiment.getParents())
				{
					Strain s = so.cast();
					System.out.println( new JSONObject(s));
					Creature c = getCreature(s, model);
					System.out.println( c );
					cratemodel.getParents().add(c);
				}
				try
                {
	                CreatureSet children = model.getMatingEngine().getProgenies(cratemodel.getName(), cratemodel.getParents(), cratemodel.getProgenies().size()+1, 1, model.getRules());
	                for( Creature c : children )
	                {
	                	cratemodel.getProgenies().add(c);
	                }
                }
                catch (MatingException e)
                {
                	JSONObject err = new JSONObject();
    				err.put("error", new JSONString("Mating exception." + e.getMessage() ));
    				exp.onError(err.getJavaScriptObject());
	                e.printStackTrace();
                }
				JSONObject ret = new JSONObject();
				ret.put("name", new JSONString(cratemodel.getName()));
				ret.put("id", new JSONString(cratemodel.getUUID()));
				ret.put("parents", getCrateSetToJSON(cratemodel.getParents(),model));
				ret.put("children", getCrateSetToJSON(cratemodel.getProgenies(),model));			
				exp.onSuccess(ret.getJavaScriptObject());
			}
			else if ("metadata".equalsIgnoreCase(exp.getExperimentCommand()))
			{
				JSONObject ret = new JSONObject();
				ret.put("error", new JSONString("Experiment does not have ID."));
				exp.onError(ret.getJavaScriptObject());
			}
			else
			{
				JSONObject ret = new JSONObject();
				ret.put("error", new JSONString("Unrecognized command."));
				exp.onError(ret.getJavaScriptObject());

			}
		}
		else
		{
			CrateModel cratemodel = find(model.getCrateSet(), id);
			JSONObject ret = new JSONObject();
			ret.put("id", new JSONString(id));
			cratemodel.setName(experiment.getName());
			if ("mate".equalsIgnoreCase(exp.getExperimentCommand()))
			{
			}
			else if ("metadata".equalsIgnoreCase(exp.getExperimentCommand()))
			{

			}
			else
			{
				JSONObject ret2 = new JSONObject();
				ret2.put("error", new JSONString("Unrecognized command."));
				exp.onError(ret2.getJavaScriptObject());

			}
			exp.onSuccess(ret.getJavaScriptObject());
		}
	}



	private final JSONValue getCrateSetToJSON(CreatureSet creatures, Model model)
    {
		JSONArray ret = new JSONArray();
		for( Creature c : creatures )
		{
			ret.set(ret.size(),StrainHelper.getLong(c, model.getRules()));
		}
	    return ret;
    }

	private final Creature getCreature(Strain s, Model model)
	{
		String id = s.getId();
		// this needs to be improved; this is a hotfix
		// need to cache creatures for performance
		Creature c = getCreature(id, model.getCreatures());
		if (c == null)
		{
			for (CrateModel cc : model.getCrateSet())
			{
				c = getCreature(id, cc.getProgenies());
				if (c != null)
				{
					break;
				}
			}
		}
		return c;
	}

	private final Creature getCreature(String id, CreatureSet creatures)
	{
		Creature ret = null;
		for (Creature c : creatures)
		{
			if (id.equals(c.getUUID()))
			{
				ret = c;
				break;
			}
		}
		return ret;
	}

	private final CrateModel find(Iterable<CrateModel> set, String id)
	{
		CrateModel ret = null;
		for (CrateModel m : set)
		{
			if (m.getUUID().equalsIgnoreCase(id))
			{
				ret = m;
				break;
			}
		}
		return ret;
	}

}
