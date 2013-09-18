package star.genetics.client;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;

import star.genetics.client.messages.Exec;
import star.genetics.genetic.model.CreatureSet;
import star.genetics.genetic.model.Model;

public class ListStrains extends Exec
{

	protected ListStrains() {};
	
	public final void execute(Model model)
    {
		JSONObject ret = new JSONObject();
		JSONArray retset = new JSONArray();
		ret.put("strains", retset);		
		CreatureSet strains = model.getCreatures();
		for (int i = 0; i < strains.size(); i++)
		{
			retset.set(i, StrainHelper.getLong(strains.get(i),model.getRules()));
		}
		this.onSuccess(ret.getJavaScriptObject());
    }

}
