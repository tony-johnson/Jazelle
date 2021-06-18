package hep.sld.jas;
import hep.analysis.*;
import hep.sld.jazelle.*;
import hep.sld.jazelle.family.*;
import java.util.Enumeration;

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

    @Override
    public Enumeration keys() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
