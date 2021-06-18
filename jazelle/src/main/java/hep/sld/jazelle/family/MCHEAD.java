package hep.sld.jazelle.family;
import hep.sld.jazelle.*;
import java.io.IOException;

public final class MCHEAD extends Bank
{
	public int read(DataBuffer data, int offset) throws IOException
	{
		this.buffer = data;
		this.offset = offset;
		return 20;
	}
	public int ntot()
	{
		return buffer.readInt(offset);
	}
	public int origin()
	{
		return buffer.readInt(offset+4);
	}
	public float ipx()
	{
		return buffer.readFloat(offset+8);
	}
	public float ipy()
	{
		return buffer.readFloat(offset+12);
	}
	public float ipz()
	{
		return buffer.readFloat(offset+16);
	}
	private DataBuffer buffer;
	private int offset;
}
