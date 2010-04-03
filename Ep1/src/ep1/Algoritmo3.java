package ep1;

import java.math.BigDecimal;
import java.util.LinkedList;

import ep1.calculadores.CalculadorAlgoritmo3;
import ep1.calculadores.ProdutorDePrimos;
import ep1.calculadores.ProdutorDePrimosGemeos;
import ep1.ed.ListaDePrimos;
import ep1.ed.MemoriaCompartilhada;

public class Algoritmo3 {
	private final int n;
	private CalculadorAlgoritmo3 consumidor;
	private int totalDeThreads;
	private LinkedList<ProdutorDePrimos> produtores;
	private ProdutorDePrimosGemeos produtorDePrimosGemeos;

	public Algoritmo3(int n, int threads) {
		this.n = n;
		totalDeThreads = threads;
		produtores = new LinkedList<ProdutorDePrimos>();
	}

	public BigDecimal calculaSoma() {
		createThreads();
		startThreads();
		return joinThreads();
	}

	private BigDecimal joinThreads() {
		try {
			consumidor.join();
			return consumidor.getSoma();
		} catch (InterruptedException e) {
			e.printStackTrace();
			return BigDecimal.ZERO;
		}
	}

	private void startThreads() {
		for (ProdutorDePrimos produtor : produtores) {
			produtor.start();
		}
		produtorDePrimosGemeos.start();
		consumidor.start();
	}

	private void createThreads() {
		ListaDePrimos listaDePrimos = new ListaDePrimos();

		MemoriaCompartilhada compartilhada = new MemoriaCompartilhada(n,
				totalDeThreads-1, listaDePrimos, false);

		for (int i = 0; i < totalDeThreads-1; i++) 
			produtores.add(new ProdutorDePrimos(compartilhada));
		produtorDePrimosGemeos = new ProdutorDePrimosGemeos(compartilhada);
		consumidor = new CalculadorAlgoritmo3(compartilhada);
	}
}
