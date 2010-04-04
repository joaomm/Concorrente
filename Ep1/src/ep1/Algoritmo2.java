package ep1;

import java.math.BigInteger;
import java.util.LinkedList;

import ep1.calculadores.CalculadorAlgoritmo2;
import ep1.eds.MemoriaCompartilhada;
import ep1.produtores.ProdutorDePrimos;

public class Algoritmo2 extends Algoritmo {
	private CalculadorAlgoritmo2 consumidor;
	private int totalDeThreads;
	private LinkedList<ProdutorDePrimos> produtores;

	public Algoritmo2(int threads) {
		nome = "Algoritmo 2";
		totalDeThreads = threads == 1 ? 1 : threads-1;
		produtores = new LinkedList<ProdutorDePrimos>();
	}

	protected void joinThreads() {
		try {
			consumidor.join();
			resultado = consumidor.getSoma();
		} catch (InterruptedException e) {
			e.printStackTrace();
			resultado = BigInteger.ZERO;
		}
	}

	protected void startThreads() {
		for (ProdutorDePrimos produtor : produtores) {
			produtor.start();
		}
		consumidor.start();
	}

	protected void createThreads() {
		MemoriaCompartilhada compartilhada = new MemoriaCompartilhada(n,
				totalDeThreads);

		for (int i = 0; i < totalDeThreads; i++) {
			produtores.add(new ProdutorDePrimos(compartilhada));
		}

		consumidor = new CalculadorAlgoritmo2(compartilhada);
	}

}
