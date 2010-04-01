package ep1;

import java.math.BigInteger;

import ep1.calculadores.CalculadorAlgoritmo2;
import ep1.calculadores.ProdutorDePrimos;
import ep1.ed.ListaDePrimos;

public class Algoritmo2 {
	private final int n;
	private ProdutorDePrimos produtor;
	private ListaDePrimos listaDePrimos;
	private CalculadorAlgoritmo2 consumidor;

	public Algoritmo2(int n, int threads) {
		this.n = n;
	}

	public BigInteger calculaSoma() {
		createThreads();
		startThreads();
		return joinThreads();
	}

	private BigInteger joinThreads() {
		try {
			consumidor.join();
			return consumidor.getSoma();
		} catch (InterruptedException e) {
			e.printStackTrace();
			return BigInteger.ZERO;
		}
	}

	private void startThreads() {
		produtor.start();
		consumidor.start();
	}

	private void createThreads() {
		listaDePrimos = new ListaDePrimos();
		produtor = new ProdutorDePrimos(n+1, listaDePrimos);
		consumidor = new CalculadorAlgoritmo2(n, listaDePrimos);
	}
}
