package ep1.calculadores;

import java.math.BigInteger;

import ep1.ed.ListaDePrimos;

public class TestadorDePrimalidade extends Thread {

	private final BigInteger candidato;
	private final ListaDePrimos lista;
	private boolean resultado;

	public TestadorDePrimalidade(BigInteger possivelPrimo,
			ListaDePrimos listaDePrimos) {
		candidato = possivelPrimo;
		lista = listaDePrimos;
	}

	@Override
	public void run() {
		resultado = lista.ehPrimo(candidato);
	}

	public boolean ehPrimo() {
		return resultado;
	}
}
