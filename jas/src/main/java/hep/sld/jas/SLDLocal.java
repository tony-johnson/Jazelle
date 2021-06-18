package hep.sld.jas;

import jas.jds.module.*;
import hep.analysis.*;

public class SLDLocal extends MultiFileLocalDIM
{
	public SLDLocal()
	{
		super("Jazelle file (*.jazelle)",".jazelle");
	}
	public EventSource openDataSet(String fileName) throws ModuleException
	{
		if (fileName.indexOf(java.io.File.pathSeparatorChar) < 0)
		{
			return new SLDEventSource(fileName);
		}
		else
		{
			return new MultiFileEventSource(fileName);
		}
	}
}
