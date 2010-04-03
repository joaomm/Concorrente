package ep1;

import java.math.BigInteger;
import java.util.Vector;

import ep1.calculadores.CalculadorAlgoritmo2;
import ep1.calculadores.ProdutorDePrimos;
import ep1.ed.Candidato;
import ep1.ed.ListaDePrimos;
import ep1.ed.MemoriaCompartilhada;

public class Algoritmo2 {
	private final int n;
	private ProdutorDePrimos produtor;
	private CalculadorAlgoritmo2 consumidor;
	private MemoriaCompartilhada compartilhada;
	private ProdutorDePrimos produtor2;

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
		produtor2.start();
		consumidor.start();
	}

	private void createThreads() {
		ListaDePrimos listaDePrimos = new ListaDePrimos();
		
		compartilhada = new MemoriaCompartilhada(n+1, listaDePrimos);
		
		produtor = new ProdutorDePrimos(compartilhada);
		produtor2 = new ProdutorDePrimos(compartilhada);
		
		consumidor = new CalculadorAlgoritmo2(n, listaDePrimos);
	}

}
