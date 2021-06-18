package hep.sld.jazelle.family;
import hep.sld.jazelle.*;
import java.io.IOException;

final public class PHCRID extends Bank
{
	public int read(DataBuffer data, int offset) throws IOException
	{
		this.buffer = data;
		this.offset = offset;
		int ctlword = data.readInt(offset);
		
		boolean liqPresent = (ctlword & 0x10000) != 0;
		boolean gasPresent = (ctlword & 0x20000) != 0;
		
		int size = 16;
		size += liq.read(data,offset+size,liqPresent);
		size += gas.read(data,offset+size,gasPresent);
		
		llik = new PIDVEC(liq.llik(),gas.llik(),data.readFloat(offset+4));
		return size;
	}
	public int rc()
	{
		return buffer.readShort(offset+8);
	}
	public int geom()
	{
		return buffer.readShort(offset+10);
	}
	public int trkp()
	{
		return buffer.readShort(offset+12);
	}
	public int nhits()
	{
		return buffer.readShort(offset+14);
	}
	public CRIDHYP liqhyp()
	{
		return liq;
	}
	public CRIDHYP gashyp()
	{
		return gas;
	}
	public PIDVEC llik()
	{
		return llik;
	}
	private PIDVEC llik;  
	private CRIDHYP liq = new CRIDHYP();
	private CRIDHYP gas = new CRIDHYP();
	private DataBuffer buffer;
	private int offset;
}
