package hep.sld.jazelle;
import java.io.IOException;

public final class DataBuffer
{
	void setData(JazelleInputStream input, int size) throws IOException
	{
		if (buffer.length < size) buffer = new byte[size];
		input.readFully(buffer,0,size); 
	}
	public int readInt(int offset)
	{
		int b1 = read(offset);
		int b2 = read(offset+1);
		int b3 = read(offset+2);
		int b4 = read(offset+3);
		return b1 + (b2 << 8) + (b3 << 16) + (b4 << 24);
	}
	public int readShort(int offset)
	{
		int b1 = read(offset);
		int b2 = read(offset+1);
		return b1 + (b2 << 8);
	}
	public float readFloat(int offset)
	{
		// Convert from VAX F_FLOAT format to IEEE format
		int fbits = readInt(offset);
		if (fbits == 0) return 0;

		int sign = fbits & 0x8000;
		int exp = fbits & 0x7f80;
		exp -= 2<<7;
		// TODO: Check for exponent overflow/underflow
		int mantissa = (fbits & 0x7f) << 16 + (fbits & 0xffff0000) >> 16;
		int bits = sign<<16 | exp<<16 | mantissa;
		return Float.intBitsToFloat(bits);
	}
	private int read(int offset)
	{
		return buffer[offset] & 0xff;
	}
	byte[] buffer = new byte[32768];
}
