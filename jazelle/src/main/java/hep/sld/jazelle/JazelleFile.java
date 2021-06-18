/*
 * File.java
 *
 * Created on June 14, 2000, 10:43 PM
 */
 
package hep.sld.jazelle;
import hep.sld.jazelle.family.*;
import hep.sld.jazelle.mdst.*;
import java.io.*;
import java.util.Date;
import java.util.zip.GZIPInputStream;

/** This class represents an open Jazelle file.
 * @author tonyj
 * @version $Id:
 */
public class JazelleFile
{
	private JazelleInputStream data;
	private Jazelle jazelle = Jazelle.instance();
	
    /** Creates new File */
	public JazelleFile(File file) throws IOException
	{
		if (file.getName().endsWith("gz")) 
		{
			data = new JazelleInputStream(new BufferedInputStream(new GZIPInputStream(new FileInputStream(file))));
		}
		else
		{
			data = new JazelleInputStream(new BufferedInputStream(new FileInputStream(file)));
		}
	}
	public void nextRecord() throws IOException
	{
		jazelle.clear();
		data.nextLogicalRecord();

		int recno = data.readInt();
		int t1 = data.readInt();
		int t2 = data.readInt();
		int target = data.readInt();
		rectype = data.readString(8);
		int p1 = data.readInt();
		int p2 = data.readInt();
		format = data.readString(8);
		String context = data.readString(8);
			
		int tocrec = data.readInt();
		int datrec = data.readInt();
		int tocsiz = data.readInt();
		int datsiz = data.readInt();
		
		int tocoff1 = data.readInt();
		int tocoff2 = data.readInt();
		int tocoff3 = data.readInt();
		int datoff = data.readInt();
			
		String segname = data.readString(8);
		String usrnam = data.readString(8);
		int usroff = data.readInt();
			
		int lrecflgs = data.readInt();
		int spare1 = data.readInt();
		int spare2 = data.readInt();
			
		// Read the event header
			
		if (usrnam.equals("IJEVHD"))
		{
			if (data.getNBytes() != usroff) throw new IOException("Inconsistent usrOff");
			
			IEVENTH ijevhd = (IEVENTH) jazelle.add("IEVENTH",1);
			ijevhd.read(data);
			//System.out.println("IEVENTH = "+ijevhd);
		}
		if (format.equals("MINIDST"))
		{
			if (data.getNBytes() != tocoff1) throw new IOException("Inconsistent tocOff");
			PHMTOC phmtoc = new PHMTOC(data);
			//System.out.println("PHMTOC = "+phmtoc+" datrec="+datrec);
			if (datrec>0) data.nextPhysicalRecord();
			if (data.getNBytes() != datoff) throw new IOException("Inconsistent datOff "+data.getNBytes()+" "+datoff);

			// Read the entire data record
			
			buffer.setData(data,datsiz);			
			MINIDST dst = new MINIDST(buffer,phmtoc);
		}
	}
	public void close() throws IOException
	{
		data.close();
	}
	public String getRecType()
	{
		return rectype;
	}
	private String getFormat()
	{
		return format;
	}
	private String rectype;
	private String format;
	DataBuffer buffer = new DataBuffer();
}
