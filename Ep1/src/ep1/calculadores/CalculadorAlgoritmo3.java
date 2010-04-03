package ep1.calculadores;

import java.math.BigDecimal;

import ep1.ed.Gemeos;
import ep1.ed.MemoriaCompartilhada;

public class CalculadorAlgoritmo3 extends Thread {

	private static final int PRECISAO = 10;
	private final MemoriaCompartilhada compartilhada;
	private BigDecimal soma;
	private BigDecimal um;

	public CalculadorAlgoritmo3(MemoriaCompartilhada compartilhada) {
		this.compartilhada = compartilhada;
		soma = BigDecimal.ZERO;
		um = BigDecimal.ONE;
	}

	@Override
	public void run() {
		for (int i = 0; i < compartilhada.getN(); i++) {
			soma = soma.add(termo(i));
		}
	}

	private BigDecimal termo(int i) {
		Gemeos gemeos = umParDeGemeos(i);
		BigDecimal primo1 = BigDecimal.valueOf(gemeos.getPrimo1());
		BigDecimal primo2 = BigDecimal.valueOf(gemeos.getPrimo2());

		int roundDown = BigDecimal.ROUND_DOWN;
		BigDecimal umSobrePrimo1 = um.divide(primo1, PRECISAO, roundDown);
		BigDecimal umSobrePrimo2 = um.divide(primo2, PRECISAO, roundDown);
		
		return umSobrePrimo1.add(umSobrePrimo2);
	}

	private Gemeos umParDeGemeos(int i) {
		return compartilhada.getGemeos(i);
	}

	public BigDecimal getSoma() {
		return soma;
	}

}
