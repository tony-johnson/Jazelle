package hep.sld.jazelle.family;
import hep.sld.jazelle.*;
import java.io.IOException;

public final class CRIDHYP
{
	public int read(DataBuffer data, int offset, boolean full) throws IOException
	{
		this.buffer = data;
		this.offset = offset;
		this.full = full;
		if (full) llik = new PIDVEC(data,offset);
		return full ? 36 : 4;
	}
	public PIDVEC llik()
	{
		return llik;
	}
	public int rc()
	{
		return buffer.readShort(full ? offset+20 : offset);
	}
	public int nhits()
	{
		return buffer.readShort(full ? offset+22 : offset+2);
	}
	public int besthyp()
	{
		return full ? buffer.readInt(offset+24) : 0;
	}
	public int nhexp()
	{
		return full ? buffer.readShort(offset+28) : 0;
	}
	public int nhfnd()
	{
		return full ? buffer.readShort(offset+30) : 0;
	}
	public int nhbkg()
	{
		return full ? buffer.readShort(offset+32) : 0;
	}
	public int mskphot()
	{
		return full ? buffer.readShort(offset+34) : 0;
	}
	private boolean full;
	private PIDVEC llik;  
	private DataBuffer buffer;
	private int offset;
}
