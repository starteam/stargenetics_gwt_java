package star.genetics.tests;

import java.io.IOException;
import java.io.InputStream;

import org.json.JSONException;
import org.json.JSONObject;

public class ToJSON
{
	static String getJSON(String name)
	{
		InputStream is = ToJSON.class.getResourceAsStream(name);
		byte[] c;
		try
		{
			c = new byte[is.available()];
			is.read(c);
			String json = new String(c);
			JSONObject data;
			data = new JSONObject(json);
			return data.toString();
		}
		catch (JSONException e)
		{
			e.printStackTrace();
			return e.toString();
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return e.toString();
		}
	}

	public static void main(String[] args)
    {
	    System.out.println(getJSON("setupmodel.json"));
    }
}
