/*
 * Family.java
 *
 * Created on June 18, 2000, 12:27 AM
 */
 
package hep.sld.jazelle;
import java.util.*;
/** 
 * Represents a Jazelle family
 * @author  tonyj
 * @version $Id:
 */
class Family
{
	/** Creates new Family */
	Family(Jazelle jazelle,String name) 
	{
		try
		{
			this.name = name;
			this.jazelle = jazelle;
			this.klass = Class.forName("hep.sld.jazelle.family."+name);
		}
		catch (ClassNotFoundException x)
		{
			throw new JazelleException("Class not found for family "+name);
		}
	}
	public Jazelle jazelle()
	{
		return jazelle;
	}
	private Bank createBank()
	{
		try
		{
			// First see if one is available in the cache
			if (free != null)
			{
				if (free.hasNext()) return (Bank) free.next();
				else free = null;
			}
			Bank result = (Bank) klass.newInstance();
			cache.add(result);
			return result;
		}
		catch (Exception x)
		{
			throw new JazelleException("Bank creation error "+x);
		}
	}
	Bank add()
	{
		return addImpl(0);
	}
	Bank add(int id)
	{
		if (id < 1 || id > 32767) throw new JazelleException("Illegal ID "+name+"("+id+")");
		return addImpl(id);
	}
	private Bank addImpl(int id)
	{
		Bank last = findLast();
		Bank bank = createBank();
		bank.setFamily(this);
		
		if (id == 0) 
		{
			if (last == null) bank.setID(1);
			else 
			{
				int nextID = last.getID()+1;
				if (nextID > 32767) throw new JazelleException("ID too large");
				bank.setID(nextID);
			}
		}
		else bank.setID(id);
		
		if (last == null) // first bank
		{
			bank.prev = null;
			bank.next = null;
			list.add(bank);
			return bank;
		}
		else if (id > last.id) // Common case!
		{
			bank.next = null;
			bank.prev = last;
			last.next = bank;
			list.add(bank);
			return bank;
		}
		else
		{
			int pos = Collections.binarySearch(list,bank);
			if (pos >= 0) throw new JazelleException("Duplicate bank id "+name+"("+bank.getID()+")");
			int insert = -pos-1;
			Bank prev = (insert > 0) ? (Bank) list.get(insert-1) : null;
			Bank next = (insert < list.size()) ? (Bank) list.get(insert) : null;
		
			if (prev != null) prev.next = bank;
			if (next != null) next.prev = bank;
			bank.prev = prev;
			bank.next = next;
		
			list.add(-pos-1, bank);
			return bank;
		}
	}
	String getName()
	{
		return name;
	}
	Bank findFirst()
	{
		if (list.isEmpty()) return null;
		return (Bank) list.get(0);
	}
	Bank findLast()
	{
		if (list.isEmpty()) return null;
		return (Bank) list.get(list.size()-1);
	}
	/**
	 * Find the bank with the given id
	 * @param id The ID to search for
	 * @return The bank or null of no bank exists with specified id
	 */
	Bank find(int id)
	{
		if (list.isEmpty()) return null;
		int maxid = findLast().id;
		if (id > maxid) return null;
		else if (id == maxid) return findLast();
		int minid = findFirst().id;
		if (id < minid) return null;
		else if (id == minid) return findFirst();
		// Guess position
		int guess = count()*(id-minid)/(maxid-minid);
		Bank current = (Bank) list.get(guess);
		if (current.id == id) return current;
		else if (current.id < id)
		{
			while (current.id < id)
			{
				current = current.next;
				if (current.id == id) return current;
			}
			return null;
		}
		else 
		{
			while (current.id > id)
			{
				current = current.prev;
				if (current.id == id) return current;
			}
			return null;
		}
	}
	Iterator iterator()
	{
		if (list.isEmpty()) return Collections.EMPTY_LIST.iterator();
		return new BankIterator();
	}
	int count()
	{
		return list.size();
	}
	void clear()
	{
		list.clear();
		free = cache.iterator();
	}
	private String name;
	private Jazelle jazelle;
	private Class klass;
	/**
	 * list is the list of banks currently in use
	 */
	private ArrayList list = new ArrayList();
	/**
	 * cache is the list of all banks allocated
	 */
	private ArrayList cache = new ArrayList();
	private Iterator free; // Initially null
	
	private class BankIterator implements Iterator
	{
		public boolean hasNext()
		{
			return current != null;
		}
		public Object next()
		{
			Bank result = current;
			current = current.next;
			return result;
		}
		public void remove()
		{
			throw new UnsupportedOperationException();
		}
		private Bank current = findFirst();
	}
}
