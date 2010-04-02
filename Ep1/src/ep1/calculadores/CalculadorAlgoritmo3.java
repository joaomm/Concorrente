package ep1.calculadores;

import java.math.BigDecimal;
import java.math.BigInteger;

import ep1.ed.Gemeos;
import ep1.ed.ListaDePrimosGemeos;

public class CalculadorAlgoritmo3 extends Thread {

	private static final int PRECISAO = 10;
	private final int n;
	private final ListaDePrimosGemeos listaDe1SobrePrimos;
	private BigDecimal soma;
	private BigDecimal um;

	public CalculadorAlgoritmo3(int n, ListaDePrimosGemeos listaDe1SobrePrimos) {
		this.n = n;
		this.listaDe1SobrePrimos = listaDe1SobrePrimos;
		soma = BigDecimal.ZERO;
		um = BigDecimal.ONE;
	}

	@Override
	public void run() {
		for (int i = 0; i < n; i++) {
			soma = soma.add(termo(i));
		}
	}

	private BigDecimal termo(int i) {
		Gemeos gemeos = umParDeGemeos(i);
		BigInteger primo1 = gemeos.getPrimo1();
		BigInteger primo2 = gemeos.getPrimo2();
		
		BigDecimal umSobrePrimo1 = um.divide(new BigDecimal(primo1), PRECISAO,
				BigDecimal.ROUND_DOWN);
		BigDecimal umSobrePrimo2 = um.divide(new BigDecimal(primo2), PRECISAO,
				BigDecimal.ROUND_DOWN);
		return umSobrePrimo1.add(umSobrePrimo2);
	}

	private Gemeos umParDeGemeos(int i) {
		return listaDe1SobrePrimos.getGemeos(i);
	}

	public BigDecimal getSoma() {
		return soma;
	}

}
