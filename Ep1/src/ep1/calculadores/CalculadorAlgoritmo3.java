package ep1.calculadores;

import java.math.BigDecimal;

import ep1.eds.Gemeos;
import ep1.eds.MemoriaCompartilhada;

public class CalculadorAlgoritmo3 extends Thread {
	private final MemoriaCompartilhada compartilhada;
	private BigDecimal soma;

	public CalculadorAlgoritmo3(MemoriaCompartilhada compartilhada) {
		this.compartilhada = compartilhada;
		soma = BigDecimal.ZERO;
	}

	@Override
	public void run() {
		for (int i = 0; i < compartilhada.getN(); i++) {
			soma = soma.add(termo(i));
		}
	}

	private BigDecimal termo(int i) {
		Gemeos gemeos = umParDeGemeos(i);
		
		BigDecimal umSobrePrimo1 = gemeos.getPrimoInvertido(1);
		BigDecimal umSobrePrimo2 = gemeos.getPrimoInvertido(2);
		
		return umSobrePrimo1.add(umSobrePrimo2);
	}

	private Gemeos umParDeGemeos(int i) {
		return compartilhada.getGemeos(i);
	}

	public BigDecimal getSoma() {
		return soma;
	}
}
