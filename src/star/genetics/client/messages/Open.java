package star.genetics.client.messages;

import star.genetics.genetic.model.Model;
import star.genetics.parser.ModelParser;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;


public class Open extends Exec
{

	protected Open() {}
	
	public final native String getProtocol()  /*-{ return this.data.protocol; }-*/;
	public final native String getJSONModel() /*-{ return JSON.stringify(this.data.model); }-*/;
	public final native JavaScriptObject getModel() /*-{ return this.data.model; }-*/;

	public final Model execute()
    {
		final String VERSION_1 = "Version_1";
		Model model = null;
		if (VERSION_1.equalsIgnoreCase(getProtocol()))
		{
			model = (ModelParser.parse(getJSONModel()));
			JSONObject ret = new JSONObject();
			onSuccess(ret.getJavaScriptObject());
		}
		else
		{
			JSONObject ret = new JSONObject();
			ret.put("error", new JSONString("Unrecognized protocol."));
			onError(ret.getJavaScriptObject()); 
		}
		return model;
    }
}
