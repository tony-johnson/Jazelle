package hep.sld.jazelle.family;
import hep.sld.jazelle.*;
import java.io.IOException;

public final class PHKELID extends Bank
{
	public int read(DataBuffer data, int offset) throws IOException
	{
		this.buffer = data;
		this.offset = offset;
		int pid = data.readInt(offset) >> 16;
		phchrg = (PHCHRG) jazelle().find("PHCHRG",pid);
		return 96;
	}
	public PHCHRG phchrg()
	{
		return phchrg;
	}
	public int idstat()
	{
		return buffer.readShort(offset+4);
	}
	public int prob()
	{
		return buffer.readShort(offset+6);
	}
	public float phi()
	{
		return buffer.readFloat(offset+8);
	}
	public float theta()
	{
		return buffer.readFloat(offset+12);
	}
	public float qp()
	{
		return buffer.readFloat(offset+16);
	}
	public float dphi()
	{
		return buffer.readFloat(offset+20);
	}
	public float dtheta()
	{
		return buffer.readFloat(offset+24);
	}
	public float dqp()
	{
		return buffer.readFloat(offset+28);
	}
	public float tphi()
	{
		return buffer.readFloat(offset+32);
	}
	public float ttheta()
	{
		return buffer.readFloat(offset+36);
	}
	public float isolat()
	{
		return buffer.readFloat(offset+40);
	}
	public float em1()
	{
		return buffer.readFloat(offset+44);
	}
	public float em12()
	{
		return buffer.readFloat(offset+48);
	}
	public float dem12()
	{
		return buffer.readFloat(offset+52);
	}
	public float had1()
	{
		return buffer.readFloat(offset+56);
	}
	public float emphi()
	{
		return buffer.readFloat(offset+60);
	}
	public float emtheta()
	{
		return buffer.readFloat(offset+64);
	}
	public float phiwid()
	{
		return buffer.readFloat(offset+68);
	}
	public float thewid()
	{
		return buffer.readFloat(offset+72);
	}
	public float em1x1()
	{
		return buffer.readFloat(offset+76);
	}
	public float em2x2a()
	{
		return buffer.readFloat(offset+80);
	}
	public float em2x2b()
	{
		return buffer.readFloat(offset+84);
	}
	public float em3x3a()
	{
		return buffer.readFloat(offset+88);
	}
	public float em3x3b()
	{
		return buffer.readFloat(offset+92);
	}
	private PHCHRG phchrg;
	private DataBuffer buffer;
	private int offset;
}
