package ep1;

import java.math.BigInteger;
import java.util.LinkedList;

import ep1.calculadores.CalculadorAlgoritmo2;
import ep1.calculadores.ProdutorDePrimos;
import ep1.ed.ListaDePrimos;
import ep1.ed.MemoriaCompartilhada;

public class Algoritmo2 {
	private final int n;
	private CalculadorAlgoritmo2 consumidor;
	private int totalDeThreads;
	private LinkedList<ProdutorDePrimos> produtores;

	public Algoritmo2(int n, int threads) {
		this.n = n;
		totalDeThreads = threads;
		produtores = new LinkedList<ProdutorDePrimos>();
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
		for (ProdutorDePrimos produtor : produtores) {
			produtor.start();
		}
		consumidor.start();
	}

	private void createThreads() {
		ListaDePrimos listaDePrimos = new ListaDePrimos();

		MemoriaCompartilhada compartilhada = new MemoriaCompartilhada(n + 1, totalDeThreads,
				listaDePrimos);

		for (int i = 0; i < totalDeThreads; i++) {
			produtores.add(new ProdutorDePrimos(compartilhada));
		}

		consumidor = new CalculadorAlgoritmo2(n, listaDePrimos);
	}

}
