/*
 * PHMTOC.java
 *
 * Created on June 17, 2000, 12:51 PM
 */
 
package hep.sld.jazelle.mdst;
import hep.sld.jazelle.*;
import java.io.IOException;

/** 
 *
 * @author  tonyj
 * @version 
 */
public class PHMTOC
{
	/** Creates new PHMTOC */
	public PHMTOC(JazelleInputStream file) throws IOException
	{
		Version = file.readFloat();
		NMcPart = file.readInt();
		NPhPSum = file.readInt();
		NPhChrg = file.readInt();
		NPhKlus = file.readInt();
		NPhKTrk = file.readInt();
		NPhWic = file.readInt();
		NPhWMC = file.readInt();
		NPhCrid = file.readInt();
		NPhPoint = file.readInt();
		NMCPnt = file.readInt();
		NPhKMC1 = file.readInt();
		NPhKChrg = file.readInt();
		NPhBm = file.readInt();
		NPhEvCl = file.readInt();
		NMCBeam = file.readInt();
		NPhKElId = file.readInt();
		NPhVxOv = file.readInt();  
	}
	public String toString()
	{
		return "Version "+Version+" nPhPSum="+NPhPSum;
	}
	float Version;
	int NMcPart;
	int NPhPSum;
	int NPhChrg;
	int NPhKlus;
 	int NPhKTrk;
	int NPhWic;
	int NPhWMC;
	int NPhCrid;
	int NPhPoint;
	int NMCPnt;
	int NPhKMC1;
	int NPhKChrg;
	int NPhBm;
	int NPhEvCl;
	int NMCBeam;
	int NPhKElId;
	int NPhVxOv;  
}
