package hep.sld.jazelle.family;
import hep.sld.jazelle.*;
import java.io.IOException;

public final class PHKLUS extends Bank
{
	public int read(DataBuffer data, int offset) throws IOException
	{
		this.buffer = data;
		this.offset = offset;
		return 96;
	}
	public int status()
	{
		return buffer.readInt(offset);
	}
	public float eraw()
	{
		return buffer.readFloat(offset+4);
	}
	public float cth()
	{
		return buffer.readFloat(offset+8);
	}
	public float wcth()
	{
		return buffer.readFloat(offset+12);
	}
	public float phi()
	{
		return buffer.readFloat(offset+16);
	}
	public float wphi()
	{
		return buffer.readFloat(offset+20);
	}
	public float elayer(int i)
	{
		return buffer.readFloat(offset+24+i*4);
	}
	public int nhit2()
	{
		return buffer.readInt(offset+56);
	}
	public float cth2()
	{
		return buffer.readFloat(offset+60);
	}
	public float wcth2()
	{
		return buffer.readFloat(offset+64);
	}
	public float phi2()
	{
		return buffer.readFloat(offset+68);
	}
	public float whphi2()
	{
		return buffer.readFloat(offset+72);
	}	
	public int nhit3()
	{
		return buffer.readInt(offset+76);
	}
	public float cth3()
	{
		return buffer.readFloat(offset+80);
	}
	public float wcth3()
	{
		return buffer.readFloat(offset+84);
	}
	public float phi3()
	{
		return buffer.readFloat(offset+88);
	}
	public float wphi3()
	{
		return buffer.readFloat(offset+92);
	}
	private DataBuffer buffer;
	private int offset;
}
