package hep.sld.jazelle;
import java.io.*;
import java.util.Date;

public class JazelleInputStream extends LogicalRecordInputStream
{
	JazelleInputStream(InputStream in) throws IOException
	{
		super(in);
		// read the header record to verify this is a legal jazelle file
		String check = readString(8);
		if (!check.equals("JAZELLE")) throw new IOException("Not JAZELLE format");
		int ibmvax = readInt();
		created = readDate();
		modified = readDate();
		nmod = readInt();
		name = readString(80);
	}
	public final void readFully(byte[] buffer, int offset, int length) throws IOException
	{
		for (int n=0; n<length; n += read(buffer,offset+n,length-n));
	}
	public final int readShort() throws IOException
	{
		int b1 = read();
		int b2 = read();
		return b1 + (b2 << 8);
	}
	public final int readInt() throws IOException
	{
		int b1 = read();
		int b2 = read();
		int b3 = read();
		int b4 = read();
		return b1 + (b2 << 8) + (b3 << 16) + (b4 << 24);
	}
	public final String readString(int len) throws IOException
	{
		byte[] b = len > buf.length ? new byte[len] : buf;
		readFully(b,0,len);
		int lenwbl;
		for (lenwbl=len; lenwbl>0; lenwbl--) 
		{
			if (b[lenwbl-1] != ' ')  break;
		}
		// As of JDK 1.3 it is much faster to use the deprecated API
		// than the new official API (which appears to look up the encoding
		// each time)
		//String result = new String(b,0,lenwbl,"US-ASCII"); 
		String result = new String(b,0,0,lenwbl);
		return result;
	}
	public final float readFloat() throws IOException
	{
		// Convert from VAX F_FLOAT format to IEEE format
		int fbits = readInt();
		if (fbits == 0) return 0;
		int sign = fbits & 0x8000;
		int exp = fbits & 0x7f80;
		exp -= 2<<7;
		// TODO: Check for exponent overflow/underflow
		int mantissa = (fbits & 0x7f) << 16 + (fbits & 0xffff0000) >> 16;
		int bits = sign<<16 | exp<<16 | mantissa;
		return Float.intBitsToFloat(bits);
	}
	public final long readLong() throws IOException
	{
		long b1 = read();
		long b2 = read();
		long b3 = read();
		long b4 = read();
		long b5 = read();
		long b6 = read();
		long b7 = read();
		long b8 = read();
		return b1 + (b2 <<  8) + (b3 << 16) + (b4 << 24)
	    +(b5<<32) + (b6 << 40) + (b7 << 48) + (b8 << 56);
		
	}
	public final Date readDate() throws IOException
	{
		long value = readLong();
		value /= 10000; // Convert to milliseconds
		value -= 3506716800730L; // Convert to Java time
		return new Date(value);
	}
	public Date getCreated()
	{
		return created;
	}
	public Date getModified()
	{
		return modified;
	}
	public int getNMod()
	{
		return nmod;
	}
	public String getName()
	{
		return name;
	}
	public static void main (String args[]) throws IOException
	{
		InputStream in = new java.util.zip.GZIPInputStream(new FileInputStream(args[0]));
		JazelleInputStream lr = new JazelleInputStream(in);
		System.out.println("Name="+lr.getName());
		System.out.println("Created="+lr.getCreated());
		System.out.println("Modified="+lr.getModified()+" ("+lr.getNMod()+")");
		
		int n=0;
		try
		{
			while (true)
			{
				n++;
				lr.nextLogicalRecord();
			}
		}
		catch (EOFException x)
		{
			System.out.println("EOF after "+n+" logical records");
		}
	}
	private byte[] buf = new byte[80];
	private Date created;
	private Date modified;
	private int nmod;
	private String name;
}
