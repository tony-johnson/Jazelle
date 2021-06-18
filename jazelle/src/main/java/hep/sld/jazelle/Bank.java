/*
 * Bank.java
 *
 * Created on June 17, 2000, 2:52 PM
 */
 
package hep.sld.jazelle;
import java.io.IOException;

/** 
 * Represents a Jazelle bank
 * @author  tonyj
 * @version $Id:
 */
public abstract class Bank implements Comparable
{
	public Bank next()
	{
		return next;
	}
	public Bank previous()
	{
		return prev;
	}
	public int getID()
	{
		return id;
	}
	public String getFamily()
	{
		return family.getName();
	}
	public int compareTo(Object o)
	{
		Bank other = (Bank) o;
		return this.id - other.id;
	}
	public Jazelle jazelle()
	{
		return family.jazelle();
	}
	public int read(DataBuffer data, int offset) throws IOException
	{
		throw new JazelleException("No read method provided for "+family.getName());
	}
	void setID(int id)
	{
		this.id = id;
	}
	void setFamily(Family family)
	{
		this.family = family;
	}
	Bank next;
	Bank prev;
	int id;
	private Family family;
}
