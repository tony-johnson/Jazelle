/*
 * MINIDST.java
 *
 * Created on June 17, 2000, 2:34 PM
 */
 
package hep.sld.jazelle.mdst;
import java.io.*;
import hep.sld.jazelle.*;

/** 
 *
 * @author  tonyj
 * @version $Id:
 */
public class MINIDST
{
	/** Creates new MINIDST */
	public MINIDST(DataBuffer data, PHMTOC toc) throws IOException
	{
		int offset = 0;
		// Read MCHead
		Bank mchead = jazelle.add("MCHEAD");
		offset += mchead.read(data,offset);
		
		// Read MCPart
		for (int i=0; i<toc.NMcPart; i++)
		{
			int id = data.readInt(offset);
			offset += 4;
			Bank mcpart = jazelle.add("MCPART",id);
			offset += mcpart.read(data,offset);
		}
		// Read PHPSUM
		for (int i=0; i<toc.NPhPSum; i++)
		{
			int id = data.readInt(offset);
			offset += 4;
			Bank phpsum = jazelle.add("PHPSUM",id);
			offset += phpsum.read(data,offset);
		}
		// Read PHCHRG
		for (int i=0; i<toc.NPhChrg; i++)
		{
			int id = data.readInt(offset);
			offset += 4;
			Bank phchrg = jazelle.add("PHCHRG",id);
			offset += phchrg.read(data,offset);
		}
		// Read PHKLUS
		for (int i=0; i<toc.NPhKlus; i++)
		{
			int id = data.readInt(offset);
			offset += 4;
			Bank phklus = jazelle.add("PHKLUS",id);
			offset += phklus.read(data,offset);
		}
		// Read PHWIC
		for (int i=0; i<toc.NPhWic; i++)
		{
			int id = data.readInt(offset);
			offset += 4;
			Bank phwic = jazelle.add("PHWIC",id);
			offset += phwic.read(data,offset);
		}
		// Read PHCRID
		for (int i=0; i<toc.NPhCrid; i++)
		{
			int id = data.readInt(offset) & 0xffff;
			Bank phcrid = jazelle.add("PHCRID",id);
			offset += phcrid.read(data,offset);
		}
		// Read PHKTRK
		for (int i=0; i<toc.NPhKTrk; i++)
		{
			int id = data.readInt(offset) & 0xffff;
			Bank phktrk = jazelle.add("PHKTRK",id);
			offset += phktrk.read(data,offset);
		}
		// Read PHKELID
		for (int i=0; i<toc.NPhKElId; i++)
		{
			int id = data.readInt(offset) & 0xffff;
			Bank phkelid = jazelle.add("PHKELID",id);
			offset += phkelid.read(data,offset);
		}
	}
	private Jazelle jazelle = Jazelle.instance();
}
