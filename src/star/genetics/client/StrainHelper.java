package star.genetics.client;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;

import star.genetics.genetic.model.Creature;

public class StrainHelper
{
	public static JSONObject getShort(Creature creature)
    {
		JSONObject ret = new JSONObject();
		ret.put( "name", new JSONString(creature.getName()));
		ret.put( "id" , new JSONString(creature.getUUID()));
		return ret;
    }
}
