package ep1.ed;

import java.math.BigInteger;

public class Gemeos {

	private final BigInteger primo1;
	private final BigInteger primo2;

	public Gemeos(BigInteger primo1, BigInteger primo2) {
		this.primo1 = primo1;
		this.primo2 = primo2;
	}

	public BigInteger getPrimo1() {
		return primo1;
	}
	
	public BigInteger getPrimo2() {
		return primo2;
	}
}
