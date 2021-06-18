package hep.sld.jazelle;
import java.io.*;

/**
 * An input stream for reading Jazelle physical records
 */
public class PhysicalRecordInputStream extends InputStream
{
	PhysicalRecordInputStream(InputStream in) throws IOException
	{
		this.in = in;
		readHeader();
	}
	final int readShortHeader() throws IOException
	{
		nBytes += 2;
		int b1 = in.read();
		int b2 = in.read();
		return b1 + (b2 << 8);
	}
	protected void readHeader() throws IOException
	{
		nBytes = 0;
		reclen = readShortHeader();
		if (reclen < 0) throw new EOFException();
		int prres = readShortHeader(); // reserved for future use		
	}
	final public int read() throws IOException
	{
		if (nBytes++ < reclen) return in.read();
		
		readHeader();
		nBytes++;
		return in.read();
	}
	final public int read(byte[] buffer, int offset, int length) throws IOException
	{
		if (nBytes == reclen) readHeader();

		int n = in.read(buffer,offset,Math.min(length,reclen-nBytes));
		nBytes += n;
		return n;
	}
	public void nextPhysicalRecord() throws IOException
	{
		// Skip the remaining bytes in this record
		for (long n = reclen-nBytes; n>0; n -= in.skip(n));
		readHeader();
	}
	public static void main (String args[]) throws IOException
	{
		InputStream in = new BufferedInputStream(new FileInputStream(args[0]));
		PhysicalRecordInputStream pr = new PhysicalRecordInputStream(in);
		int n=0;
		try
		{
			while (true)
			{
				n++;
				pr.nextPhysicalRecord();
			}
		}
		catch (EOFException x)
		{
			System.out.println("EOF after "+n+" physical records");
		}
	}
	public int getNBytes()
	{
		return nBytes;
	}
	private int nBytes;
	private int reclen;
	private InputStream in;
}
