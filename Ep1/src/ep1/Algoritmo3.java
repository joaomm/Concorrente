package ep1;

import java.math.BigDecimal;
import java.util.LinkedList;

import ep1.calculadores.CalculadorAlgoritmo3;
import ep1.eds.MemoriaCompartilhada;
import ep1.produtores.ProdutorDePrimos;
import ep1.produtores.ProdutorDePrimosGemeos;

public class Algoritmo3  extends Algoritmo{
	private CalculadorAlgoritmo3 consumidor;
	private int totalDeThreads;
	private LinkedList<ProdutorDePrimos> produtores;
	private ProdutorDePrimosGemeos produtorDePrimosGemeos;

	public Algoritmo3(int threads) {
		nome = "Algoritmo 3";
		totalDeThreads = threads;
		produtores = new LinkedList<ProdutorDePrimos>();
	}

	protected void joinThreads() {
		try {
			consumidor.join();
			resultado = consumidor.getSoma();
		} catch (InterruptedException e) {
			e.printStackTrace();
			resultado = BigDecimal.ZERO;
		}
	}

	protected void startThreads() {
		for (ProdutorDePrimos produtor : produtores) {
			produtor.start();
		}
		produtorDePrimosGemeos.start();
		consumidor.start();
	}

	protected void createThreads() {
		MemoriaCompartilhada compartilhada = new MemoriaCompartilhada(n,
				totalDeThreads-1, false);

		for (int i = 0; i < totalDeThreads-1; i++) 
			produtores.add(new ProdutorDePrimos(compartilhada));
		produtorDePrimosGemeos = new ProdutorDePrimosGemeos(compartilhada);
		
		consumidor = new CalculadorAlgoritmo3(compartilhada);
	}
}
