package ep1.eds;

import java.math.BigDecimal;

public class Gemeos {
	private final long primo1;
	private final long primo2;
	private static final BigDecimal um = BigDecimal.ONE;
	private static final int PRECISAO = 10;
	private static final int roundDown = BigDecimal.ROUND_DOWN;

	public Gemeos(long primo1, long primo2) {
		this.primo1 = primo1;
		this.primo2 = primo2;
	}

	public BigDecimal getPrimoInvertido(int i) {
		BigDecimal primo = BigDecimal.valueOf(getPrimo(i));
		return um.divide(primo, PRECISAO, roundDown);
	}
	
	private long getPrimo(int i) {
		return i == 1 ? primo1 : primo2;
	}
}
