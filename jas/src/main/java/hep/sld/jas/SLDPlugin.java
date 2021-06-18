package hep.sld.jas;
import jas.plugin.*;

public class SLDPlugin extends ExtensionPlugin
{
	public void init()
	{
		registerDIM(new SLDLocal());
	}	
}
