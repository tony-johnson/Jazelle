package hep.sld.jazelle;
import java.io.*;

public class LogicalRecordInputStream extends PhysicalRecordInputStream
{
	LogicalRecordInputStream(InputStream in) throws IOException
	{
		super(in);
	}
	protected void readHeader() throws IOException
	{
		super.readHeader();
		int lrlen = readShortHeader();
		int lrcnt = readShortHeader();
		if ((lrcnt & 0xfffffffc) != 0) throw new IOException("IOSYNCH1");
		boolean continued = (lrcnt & 2) != 0;
		if (continued != toBeContinued) throw new IOException("IOSYNCH2 "+continued+" "+toBeContinued);
		toBeContinued = (lrcnt & 1) != 0;
	}
	public void nextLogicalRecord() throws IOException
	{
		while (toBeContinued) nextPhysicalRecord();
		nextPhysicalRecord();	
	}
	public static void main (String args[]) throws IOException
	{
		InputStream in = new BufferedInputStream(new FileInputStream(args[0]));
		LogicalRecordInputStream lr = new LogicalRecordInputStream(in);
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
	private boolean toBeContinued;
}
