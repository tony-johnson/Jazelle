/*
 * Jazelle.java
 *
 * Created on June 18, 2000, 12:20 AM
 */
 
package hep.sld.jazelle;
import java.util.*;
import java.io.PrintWriter;
/** 
 *
 * @author  tonyj
 * @version 
 */
public class Jazelle
{
	/** Creates new Jazelle */
  	private Jazelle() 
	{
	}
	void clear()
	{
		Iterator iter = hash.values().iterator();
		while (iter.hasNext())
		{
			Family family = (Family) iter.next();
			family.clear();
		}
	}
	private Family family(String name)
	{
		Family family = (Family) hash.get(name);
		if (family == null)
		{
			family = new Family(this,name);
			hash.put(name,family);
		}
		return family;
	}
	public Bank find(String family, int id)
	{
		return family(family).find(id);
	}
	public Bank findFirst(String family)
	{
		return family(family).findFirst();
	}
	public Bank findLast(String family)
	{
		return family(family).findLast();
	}
	public Iterator iterator(String family)
	{
		return family(family).iterator();
	}
	public Bank add(String family)
	{
		return family(family).add();
	}
	public Bank add(String family, int id)
	{
		return family(family).add(id);
	}
	public int bankCount(String family)
	{
		return family(family).count();
	}
	public static Jazelle instance()
	{
		return jazelle;
	}
	public void index()
	{
		index(0);
	}
	public void index(PrintWriter out)
	{
		index(out,0);
	}
	public void index(int level)
	{	
		PrintWriter pw = new PrintWriter(System.out);
		index(pw,level);
		pw.close();
	}
	public void index(PrintWriter out, int level)
	{
		out.println("Jazelle bank index");
		Iterator iter = hash.values().iterator();
		while (iter.hasNext())
		{
			Family family = (Family) iter.next();
			out.println(family.getName()+" NBanks="+family.count());
			if (level > 0)
			{
				for (Bank bank = family.findFirst(); bank != null; bank = bank.next)
				{
					out.println(family.getName()+" id="+bank.id);
				}
			}
		}
	}
	private HashMap hash = new HashMap();
	private final static Jazelle jazelle = new Jazelle();
}
