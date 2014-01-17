package star.genetics.client.messages;

import java.util.logging.Level;
import java.util.logging.Logger;

import star.genetics.client.StarGenetics;
import star.genetics.genetic.model.Model;
import star.genetics.parser.ModelParser;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;

public class Open extends Exec
{
	protected Open()
	{
	}

	public final native String getProtocol() /*-{ return this.data.protocol; }-*/;

	public final native String getJSONModel() /*-{ return JSON.stringify(this.data.model); }-*/;

	public final native JavaScriptObject getModel() /*-{ return this.data.model; }-*/;

	public final Model execute(StarGenetics starGenetics)
	{
		Logger logger = Logger.getLogger("Open");
		final String VERSION_1 = "Version_1";
		Model model = null;
		logger.log(Level.INFO, "Open 1");
		if (VERSION_1.equalsIgnoreCase(getProtocol()))
		{
			logger.log(Level.INFO, "Open 2");
			model = ModelParser.parse(getJSONModel());
			logger.log(Level.INFO, "Open 3");
			JSONObject ret = new JSONObject();
			logger.log(Level.INFO, "Open 4");
			starGenetics.setModel(model);
			logger.log(Level.INFO,"Open 5");
			onSuccess(ret.getJavaScriptObject());
			logger.log(Level.INFO, "Open 6");
		}
		else
		{
			JSONObject ret = new JSONObject();
			ret.put("error", new JSONString("Unrecognized protocol."));
			onError(ret.getJavaScriptObject());
		}
		logger.log(Level.INFO, "Open 7");
		return model;
	}
}
