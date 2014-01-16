package star.genetics.client.messages;

import star.genetics.client.StarGenetics;
import star.genetics.genetic.model.Model;
import star.genetics.genetic.model.ModelWriter;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.google.web.bindery.autobean.shared.AutoBeanUtils;

public class Save extends Exec
{

	protected Save()
	{
	}

	public final native String getProtocol() /*-{ return this.data.protocol; }-*/;

	public final String serialize(ModelWriter model)
	{

		AutoBean<Model> m = AutoBeanUtils.getAutoBean(model);
		return AutoBeanCodex.encode(m).getPayload();
	}

	public final void execute(StarGenetics starGenetics, Model model)
	{
		final String VERSION_1 = "Version_1";
		if (VERSION_1.equalsIgnoreCase(getProtocol()))
		{
			JSONObject ret = new JSONObject();
			ret.put("model", new JSONString(serialize((ModelWriter) model)));
			ret.put("model2", new JSONString("test"));
			starGenetics.setModel(model);
			onSuccess(ret.getJavaScriptObject());
		}
		else
		{
			JSONObject ret = new JSONObject();
			ret.put("error", new JSONString("Unrecognized protocol."));
			onError(ret.getJavaScriptObject());
		}
	}

}
