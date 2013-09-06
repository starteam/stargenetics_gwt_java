package star.genetics.client;

import javax.management.RuntimeErrorException;

import com.google.gwt.core.client.JavaScriptObject;

public class Exec extends JavaScriptObject
{
	protected Exec()
	{
		super();
	}

	public final native String getToken() /*-{
		return this.token;
	}-*/;

	public final native String getCommand() /*-{
		return this.command;
	}-*/;

	final void onSuccess(JavaScriptObject json)
	{
		if (!onSuccessNative(json))
		{
			System.out.println(json);
		}

	}

	private final native boolean onSuccessNative(JavaScriptObject json)
	/*-{
		if (this.callbacks) {
			this.callbacks.onsuccess({
				source : this,
				payload : json
			});
			return true;
		} else {
			return false;
		}
	}-*/;

	final void onError(JavaScriptObject json)
	{
		System.out.println( "onError");
		if (!onErrorNative(json))
		{
			System.out.println(json);
			throw new RuntimeException("" + json);
		}
	}

	private final native boolean onErrorNative(JavaScriptObject json)
	/*-{
		if (this.callbacks) {
			this.callbacks.onerror({
				source : this,
				payload : json
			});
			return true;
		}
		else
		{
			return false;
		}
	}-*/;
}