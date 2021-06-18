/*
 * IJEVHD.java
 *
 * Created on June 17, 2000, 11:29 AM
 */
 
package hep.sld.jazelle.family;
import java.util.Date;
import java.io.IOException;
import hep.sld.jazelle.*;

/** 
 *
 * @author  tonyj
 * @version 
 */
public final class IEVENTH extends Bank
{
	public void read(JazelleInputStream file) throws IOException
	{
		HEADER = file.readInt();
		RUN = file.readInt();
		EVENT = file.readInt();
		EVTTIME = file.readDate();
		WEIGHT = file.readFloat();
		EVTTYPE = file.readInt();
		TRIGGER = file.readInt();
	}
	public int getRun()
	{
		return RUN;
	}
	public int getEvent()
	{
		return EVENT;
	}
	public Date getDate()
	{
		return EVTTIME;
	}
	public float getWeight()
	{
		return WEIGHT;
	}
	public int getEvtType()
	{
		return EVTTYPE;
	}
	public int getTrigger()
	{
		return TRIGGER;
	}
	public String toString()
	{
		return "Run="+RUN+" Event="+EVENT+" time="+EVTTIME;
	}
	private int   HEADER;   //"Pointer to header bank in this segment"
	private int   RUN;      //"Run number"
	private int   EVENT;    //"Event number"
	private Date  EVTTIME;  //"Time when event was created"
	private float WEIGHT;   //"Event weight (1.0 for real data)"
	private int   EVTTYPE;  //"Event type"
	private int   TRIGGER;  //"Trigger mask for this event"
}
