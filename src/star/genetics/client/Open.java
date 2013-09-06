package star.genetics.client;

import com.google.gwt.core.client.JavaScriptObject;


public class Open extends Exec
{
	public final static String VERSION_1 = "Version_1";
	protected Open() {}
	
	public final native String getProtocol()  /*-{ return this.data.protocol; }-*/;
	public final native String getJSONModel() /*-{ return JSON.stringify(this.data.model); }-*/;
	public final native JavaScriptObject getModel() /*-{ return this.data.model; }-*/;
}
