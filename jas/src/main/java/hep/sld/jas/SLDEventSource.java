/*
 * SLDEventSource.java
 *
 * Created on June 19, 2000, 2:06 PM
 */
 
package hep.sld.jas;
import jas.jds.module.*;
import jas.util.NestedRuntimeException;
import hep.analysis.*;
import java.io.*;
import java.util.zip.*;
import hep.sld.jazelle.*;

/** 
 * An JAS EventSource for reading SLD minidst files.
 * @author  tonyj
 * @version 
 */
public class SLDEventSource implements EventSource
{
	/** Creates new SLDEventSource */
	public SLDEventSource(String file) throws ModuleException
	{
		try
		{
			filename = file;
			input = new JazelleFile(new File(file));		
			atBOF = true;
		}
		catch (IOException x)
		{
			throw new ModuleException("Error opening "+file,x);
		}
	}
	public void close()
	{
		try
		{
			if (input != null) input.close();
		}
		catch (IOException x) {} 
	}
	public EventData getNextEvent() throws EndOfDataException
	{
		try
		{
			atBOF = false;
			while (true)
			{
				input.nextRecord();
				if (input.getRecType().equals("EVENT")) break;
			}
			return event;
		}
		catch (EOFException x)
		{
			throw new EndOfDataException();
		}
		catch (IOException x)
		{
			throw new NestedRuntimeException("Read error",x);
		}
	}
	public Class getEventDataClass()
	{
		return SLDEvent.class;
	}
	public int getTotalNumberOfEvents()
	{
		return -1; // We dont know until we read them!
	}
	public String getName()
	{
		return filename; 
	}
	public void beforeFirstEvent()
	{
		if (!atBOF)
		{
			try
			{
				input.close();
				input = new JazelleFile(new File(filename));;
				atBOF = true;
			}
			catch (IOException x)
			{
				throw new NestedRuntimeException("Read error",x);
			}
		}
	}
	public void afterLastEvent()
	{
	}
	private SLDEvent event = new SLDEvent();
	private JazelleFile input;
	private String filename;
	private boolean atBOF; 
}
