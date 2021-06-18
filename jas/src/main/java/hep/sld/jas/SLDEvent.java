package hep.sld.jas;
import hep.analysis.*;
import hep.sld.jazelle.*;
import hep.sld.jazelle.family.*;

public class SLDEvent implements EventHeader
{
	public void put(String p1, Object p2)
	{
		// TODO: Add your own implementation.
	}

	public Object get(String p1)
	{
		// TODO: Add your own implementation.
		return null;
	}

	public int getRunNumber()
	{
		IEVENTH event = (IEVENTH) jazelle.find("IEVENTH",1);
		return event.getRun();
	}
	public int getEventNumber()
	{
		IEVENTH event = (IEVENTH) jazelle.find("IEVENTH",1);
		return event.getEvent();
	}
	public java.util.Properties getProperties()
	{
		return null;
	}
	private Jazelle jazelle = Jazelle.instance();
}
