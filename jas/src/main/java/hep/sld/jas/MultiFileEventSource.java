package hep.sld.jas;

import hep.sld.jazelle.*;
import jas.jds.module.*;
import jas.util.NestedRuntimeException;
import hep.analysis.*;
import java.io.*;
import java.util.*;

public class MultiFileEventSource implements EventSource, FilenameFilter
{
	public MultiFileEventSource(String fileName, String filePath) throws ModuleException
	{
		this.filename = fileName;
		try
		{
			int index = filePath.lastIndexOf(File.separator);
			String directory = filePath.substring(0,index);
			prefix = filePath.substring(index+1);

			File dir = new File(directory);
			dir.list(this); // calls accept for each file in the directory

			fileIndex = 0;

			openNextFile();
			atBOF = true;
		}
		catch (IOException x)
		{
			throw new ModuleException("Error opening "+fileName,x);
		}
	}
	/**
	 * Builds an event source from a path-separator delimited list of files
	 */
	MultiFileEventSource(String path) throws ModuleException
	{
		this.filename = "Multi-File Dataset";
		try
		{
			StringTokenizer t = new StringTokenizer(path,File.pathSeparator);
			while (t.hasMoreTokens())
			{
				dataSetList.addElement(t.nextToken());
			}
			fileIndex = 0;

			openNextFile();
			atBOF = true;
		}
		catch (IOException x)
		{
			throw new ModuleException("Error opening "+filename,x);
		}
	}
	private void openNextFile() throws IOException
	{
		if (input != null) input.close();
		input = new JazelleFile(new File((String) dataSetList.elementAt(fileIndex)));
		fileIndex++;
	}
	public boolean accept(File dir, String name)
	{
		if (name.startsWith(prefix)) dataSetList.addElement(dir+File.separator+name);
		return true;
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
				if (fileIndex < dataSetList.size()) 
				{
					openNextFile();
					while (true)
					{
						input.nextRecord();
						if (input.getRecType().equals("EVENT")) break;
					}
					return event;
				}
				else throw new EndOfDataException();
			}
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
		return -1;
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
				fileIndex = 0;
				openNextFile();
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
	private int fileIndex;
	private SLDEvent event = new SLDEvent();
	private Vector dataSetList = new Vector();
	private JazelleFile input;
	private String filename;
	private boolean atBOF; 
	private String prefix;
}
