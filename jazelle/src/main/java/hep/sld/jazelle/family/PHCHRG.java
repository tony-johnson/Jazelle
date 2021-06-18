package hep.sld.jazelle.family;
import hep.sld.jazelle.*;
import java.io.IOException;

public final class PHCHRG extends Bank
{
	public int read(DataBuffer data, int offset) throws IOException
	{
		this.buffer = data;
		this.offset = offset;
		return 236;
	}
	public float hlxpar(int i)
	{
		return buffer.readFloat(offset+i*4);
	}
	public float dhlxpar(int i)
	{
		return buffer.readFloat(offset+24+i*4);
	}
	public float bnorm()
	{
		return buffer.readFloat(offset+84);
	}
	public float impact()
	{
		return buffer.readFloat(offset+88);
	}
	public float b3norm()
	{
		return buffer.readFloat(offset+92);
	}
	public float impact3()
	{
		return buffer.readFloat(offset+96);
	}
	public int charge()
	{
		return buffer.readShort(offset+100);
	}
	public int smwstat()
	{
		return buffer.readShort(offset+102);
	}
	public int status()
	{
		return buffer.readInt(offset+104);
	}	
	public float tkpar0()
	{
		return buffer.readFloat(offset+108);
	}
	public float tkpar(int i)
	{
		return buffer.readFloat(offset+112+i*4);
	}
	public float dtkpar(int i)
	{
		return buffer.readFloat(offset+132+i*4);
	}
	public float length()
	{
		return buffer.readFloat(offset+192);
	}
	public float chi2dt()
	{
		return buffer.readFloat(offset+196); 
	}
	public int imc()
	{
   		return buffer.readShort(offset+200); 
	}
	public int ndfdt()
	{
   		return buffer.readShort(offset+202); 
	}
	public int nhit()
	{
   		return buffer.readShort(offset+204);
	}
	public int nhite()
	{
   		return buffer.readShort(offset+206); 
	}
	public int nhitp()
	{
   		return buffer.readShort(offset+208);
	}
	public int nmisht()
	{
   		return buffer.readShort(offset+210); 
	}
	public int nwrght()
	{
   		return buffer.readShort(offset+212);
	}
	public int nhitv()
	{
		return buffer.readShort(offset+214);
	}
	public float chi2()
	{
		return buffer.readFloat(offset+216);
	}
	public float chi2v()
	{
		return buffer.readFloat(offset+220);
	}
	public int vxdhit()
	{
		return buffer.readInt(offset+224);
	}
	public int mustat()
	{
		return buffer.readShort(offset+228);
	}
	public int estat()
	{
		return buffer.readShort(offset+230);
	}
	public int dedx()
	{
		return buffer.readInt(offset+232);
	}
	private DataBuffer buffer;
	private int offset;
}
