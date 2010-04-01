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
		long t1 = System.currentTimeMillis();
		produtor.start();
		try {
			produtor.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		long t2 = System.currentTimeMillis();
		consumidor.start();
		try {
			consumidor.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long t3 = System.currentTimeMillis();
		long e1 = t2 - t1;
		long e2 = t3 - t2;
		System.out.println("produtor demorou: "+e1+" consumidor demorou: "+e2);
	}

	private void createThreads() {
		listaDePrimos = new ListaDePrimos();
		produtor = new ProdutorDePrimos(n+1, listaDePrimos);
		consumidor = new CalculadorAlgoritmo2(n, listaDePrimos);
	}
}
