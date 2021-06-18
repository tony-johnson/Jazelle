/*
 * JazelleException.java
 *
 * Created on June 18, 2000, 10:40 AM
 */

package hep.sld.jazelle;

/** 
 *
 * @author  tonyj
 * @version $Id:
 */
public class JazelleException extends RuntimeException 
{
  /**
   * Constructs an <code>JazelleException</code> with the specified detail message.
   * @param msg the detail message.
   */
	JazelleException(String msg) 
	{
		super(msg);
  	}
}

