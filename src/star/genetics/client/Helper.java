package star.genetics.client;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import star.genetics.genetic.model.Creature;
import star.genetics.genetic.model.ModelModifiedProvider;
import star.genetics.visualizers.Visualizer;
import star.genetics.visualizers.VisualizerFactory;

public class Helper
{
	public static void setVisualizerFromCreature(Visualizer v, Creature c)
	{
		v.setName(c.getName());
		v.setNote(c.getNote());
		v.setProperties(c.getProperties(), c.getSex());
	}

	public static void setVisualizerFromCreature(Visualizer v, Creature c, HashMap<String, String> additional)
	{
		v.setName(c.getName());
		v.setNote(c.getNote());
		// additional.putAll(c.getProperties());
		HashMap<String, String> prop = new HashMap<String, String>();
		prop.putAll(c.getProperties());
		prop.putAll(additional);
		v.setProperties(prop, c.getSex());
	}

	public static Map<String, String> parse(String value)
	{
		Map<String, String> ret = new TreeMap<String, String>();
		if (value != null)
		{
			if (value.contains("=")) //$NON-NLS-1$
			{
				String elements[] = value.split(","); //$NON-NLS-1$
				for (String element : elements)
				{
					if (element.indexOf('=') != -1)
					{
						String[] pair = element.split("=", 2); //$NON-NLS-1$
						ret.put(pair[0], pair[1]);
					}
					else
					{
						throw new RuntimeException();
						//throw new RuntimeException(MessageFormat.format(Messages.getString("Helper.0"), element, value)); //$NON-NLS-1$
					}
				}
			}
			else if (!value.equalsIgnoreCase("wildtype")) //$NON-NLS-1$
			{
				throw new RuntimeException(Messages.getString("Helper.5")); //$NON-NLS-1$
			}
		}
		return ret;
	}

	public static String export(Map<String, String> source)
	{
		StringBuffer sb = new StringBuffer();
		for (Entry<String, String> entry : source.entrySet())
		{
			sb.append(entry.getKey() + "=" + entry.getValue() + ","); //$NON-NLS-1$ //$NON-NLS-2$
		}
		sb.setLength(sb.length() - 1);
		return sb.toString();
	}

	private static String MODEL_PROVIDER_EXCEPTION = "Model Modified Provider not found"; //$NON-NLS-1$

}
