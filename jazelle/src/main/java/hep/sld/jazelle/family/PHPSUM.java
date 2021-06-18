package hep.sld.jazelle.family;
import hep.sld.jazelle.*;
import java.io.IOException;

public final class PHPSUM extends Bank
{
	public int read(DataBuffer data, int offset) throws IOException
	{
		this.buffer = data;
		this.offset = offset;
		return 32;
	}
	public float px()
	{
		return buffer.readFloat(offset);
	}
	public float py()
	{
		return buffer.readFloat(offset+4);
	}
	public float pz()
	{
		return buffer.readFloat(offset+8);
	}
	public float x()
	{
		return buffer.readFloat(offset+12);
	}
	public float y()
	{
		return buffer.readFloat(offset+16);
	}
	public float z()
	{
		return buffer.readFloat(offset+20);
	}	
	public float charge()
	{
		return buffer.readFloat(offset+24);
	}
	public int status()
	{
		return buffer.readInt(offset+28);
	}
	public double getPTot()
	{
		float px = px();
		float py = py();
		float pz = pz();
		return Math.sqrt(px*px+py*py+pz*pz);
	}
	private DataBuffer buffer;
	private int offset;
}
