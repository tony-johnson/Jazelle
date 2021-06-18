package hep.sld.jas;

import hep.analysis.EventSource;
import jas.jds.module.*;
import java.io.File;

public class SLDEventServer extends BasicServer
{
	public static void main(String[] args)
	{
		SLDEventServer f = new SLDEventServer(args);
		ServerRegistry.registerServer(f);
	}
	private SLDEventServer(String[] args)
	{
		super(args,"SLD Jazelle files","jazelle");
	}
	public EventSource createEventSource(String fullName) throws ModuleException
	{
		return new SLDEventSource(fullName);
	}
	public boolean accept(File dir, String name)
	{
		System.out.println(name);
		return super.accept(dir,name);
	}
}
