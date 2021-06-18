package hep.sld.jazelle.family;
import hep.sld.jazelle.*;
import java.io.IOException;

public final class PHWIC extends Bank
{
	public int read(DataBuffer data, int offset) throws IOException
	{
		this.buffer = data;
		this.offset = offset;
		return 140;
	}
	public int idstat()
	{
		return buffer.readShort(offset);
	}
	public int nhit()
	{
		return buffer.readShort(offset+2);
	}
	public int nhit45()
	{
		return buffer.readShort(offset+4);
	}
	public int npat()
	{
		return buffer.readShort(offset+6);
	}
	public int nhitpat()
	{
		return buffer.readShort(offset+8);
	}
	public int syshit()
	{
		return buffer.readShort(offset+10);
	}
	public float qpinit()
	{
		return buffer.readFloat(offset+12);
	}
	public float t1()
	{
		return buffer.readFloat(offset+16);
	}
	public float t2()
	{
		return buffer.readFloat(offset+20);
	}
	public float t3()
	{
		return buffer.readFloat(offset+24);
	}
	public int hitmiss()
	{
		return buffer.readInt(offset+28);
	}
	public float itrlen()
	{
		return buffer.readFloat(offset+32);
	}
	public int nlayexp()
	{
		return buffer.readShort(offset+36);
	}
	public int nlaybey()
	{
		return buffer.readShort(offset+38);
	}
	public float missprob()
	{
		return buffer.readFloat(offset+40);
	}
	public int phwicid()
	{	
		return buffer.readInt(offset+44);
	}
	public int nhitshar()
	{
		return buffer.readShort(offset+48);
	}
	public int nother()
	{
		return buffer.readShort(offset+50);
	}
	public int hitsused()
	{
		return buffer.readInt(offset+52);
	}
	public float pref1(int i)
	{
		return buffer.readFloat(offset+56+i*4);
	}
	public float pfit(int i)
	{
		return buffer.readFloat(offset+68+i*4);
	}
	public float dpfit(int i)
	{
		return buffer.readFloat(offset+84+i*4);
	}
	public float chi2()
	{
		return buffer.readFloat(offset+124);
	}
	public int ndf()
	{
		return buffer.readShort(offset+128);
	}
	public int punfit()
	{
		return buffer.readShort(offset+130);
	}
	public float matchChi2()
	{
		return buffer.readFloat(offset+132);
	}
	public int matchNdf()
	{
		return buffer.readShort(offset+136);
	}
	private DataBuffer buffer;
	private int offset;
}
