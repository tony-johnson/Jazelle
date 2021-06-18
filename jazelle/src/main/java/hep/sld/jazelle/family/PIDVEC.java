package hep.sld.jazelle.family;
import java.io.IOException;
import hep.sld.jazelle.*;

public final class PIDVEC
{
	PIDVEC(DataBuffer data, int offset) throws IOException
	{
		E = data.readFloat(offset);
		MU = data.readFloat(offset+4);
		PI = data.readFloat(offset+8);
		K = data.readFloat(offset+12);
		P = data.readFloat(offset+16);
	}
	PIDVEC(PIDVEC LIQ, PIDVEC GAS, float norm)
	{
		E = norm;
		MU = norm;
		PI = norm;
		K = norm;
		P = norm;
		if (LIQ != null) add(LIQ);
		if (GAS != null) add(GAS);
	}
	private void add(PIDVEC vec)
	{
		E += vec.E;
		MU += vec.MU;
		PI += vec.PI;
		K += vec.K;
		P += vec.P;
	}
	private float E;
	private float MU;
	private float PI;
	private float K;
	private float P;
}
