package star.genetics.client;

import java.util.Map;
import java.util.Map.Entry;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;

import star.genetics.genetic.model.Creature;
import star.genetics.genetic.model.Rule;
import star.genetics.genetic.model.RuleSet;

public class StrainHelper
{
	public static JSONObject getShort(Creature creature)
    {
		JSONObject ret = new JSONObject();
		ret.put( "name", new JSONString(creature.getName()));
		ret.put( "id" , new JSONString(creature.getUUID()));
		ret.put( "export_type" , new JSONString("short"));
		return ret;
    }
	
	public static JSONObject getLong(Creature creature, RuleSet rules)
	{
		JSONObject ret = new JSONObject();
		Map<String,String> properties = rules.getProperties(creature.getMakeup(), creature.getSex());
		for( Entry<String,String> e : properties.entrySet() )
		{
			ret.put(e.getKey(), new JSONString(e.getValue()));
		}
		ret.put( "name", new JSONString(creature.getName()));
		ret.put( "id" , new JSONString(creature.getUUID()));
		ret.put( "export_type" , new JSONString("long"));
		
		return ret;
		
	}
}
