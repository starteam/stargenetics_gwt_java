package star.genetics.client;

import java.util.logging.Level;
import java.util.logging.Logger;

import star.genetics.client.messages.Exec;
import star.genetics.client.messages.Open;
import star.genetics.client.messages.Save;
import star.genetics.client.messages.UpdateExperiment;
import star.genetics.client.messages.UpdateExperimentImpl;
import star.genetics.genetic.model.Model;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;

public class StarGenetics
{
	enum Commands
	{
		Open, Save, ListStrains, UpdateExperiment;

		public boolean is(String command)
		{
			return this.toString().equalsIgnoreCase(command);
		}
	}

	private static Logger logger = Logger.getLogger("StarGenetics");
	private Model model;

	public void setModel(Model model)
	{
		this.model = model;
	}

	private Model getModel()
	{
		return model;
	}

	public void execute(Exec exec)
	{
		try
		{
			logger.log(Level.FINE, "in Execute.");
			String command = exec.getCommand();
			System.out.println("command " + command);
			if (Commands.Open.is(command))
			{
				open(exec);
			}
			else if (Commands.ListStrains.is(command))
			{
				listStrains(exec);
			}
			else if (Commands.UpdateExperiment.is(command))
			{
				updateExperiment(exec);
			}
			else if ((Commands.Save.is(command)))
			{
				save(exec);
			}
		}
		catch (Throwable t)
		{
			t.printStackTrace();
			JSONObject ret = new JSONObject();
			ret.put("error", new JSONString(t.getMessage()));
			exec.onError(ret.getJavaScriptObject());

		}
	}

	private void save(Exec exec)
	{
		Save cmd = exec.cast();
		cmd.execute(this, getModel());

	}

	private void open(Exec exec)
	{
		Open cmd = exec.cast();
		cmd.execute(this);
	}

	private void listStrains(Exec exec)
	{
		ListStrains cmd = exec.cast();
		cmd.execute(getModel());
	}

	private void updateExperiment(Exec exec)
	{
		UpdateExperiment cmd = exec.cast();
		log("updateExperiment 2, model is:" + getModel());
		new UpdateExperimentImpl(cmd).execute(getModel());
		log("updateExperiment 3");
	}

	private static native void log(String str)
	/*-{
		if( typeof(console) == 'object' && console && console.info )
		{
			console.info( str ) ;
		}
	}-*/;

}