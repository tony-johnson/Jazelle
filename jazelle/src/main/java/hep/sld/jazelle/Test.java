/*
 * Test.java
 *
 * Created on June 14, 2000, 11:05 PM
 */
 
package hep.sld.jazelle;
import java.io.*;

/** 
 *
 * @author  tonyj
 * @version 
 */
public class Test
{
	/** Creates new Test */
	public Test()
	{
  	}
  
	/**
  	* @param args the command line arguments
  	*/
	public static void main (String args[]) throws IOException
	{
		File f = new File(args[0]);
		JazelleFile jf = new JazelleFile(f);
		int n=0;
		try
		{	
			long start = System.currentTimeMillis();
			while (true)
			{
				jf.nextRecord();
				if (jf.getRecType().equals("EVENT")) n++;
				else System.out.println("rectype "+jf.getRecType()+" ignored");
				if (n%100 == 0) 
				{
					long t = System.currentTimeMillis() - start;
					System.out.println("Read "+n+" events in "+(t/1000.)+" seconds");
				}
			}
		}
		catch (EOFException x)
		{
			System.out.println("EOF after "+n+" events!");
			jf.close();
		}
	}
}
