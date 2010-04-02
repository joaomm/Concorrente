package ep1;

import java.math.BigDecimal;

import ep1.calculadores.CalculadorAlgoritmo3;
import ep1.calculadores.ProdutorDePrimosGemeos;
import ep1.ed.ListaDePrimosGemeos;

public class Algoritmo3 {
	private final int n;
	private ProdutorDePrimosGemeos produtor;
	private ListaDePrimosGemeos listaDePrimosGemeos;
	private CalculadorAlgoritmo3 consumidor;

	public Algoritmo3(int n, int threads) {
		this.n = n;
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
		try {
			long t1 = System.currentTimeMillis();
			produtor.start();
			produtor.join();
			long t2 = System.currentTimeMillis();
			consumidor.start();
			consumidor.join();
			long t3 = System.currentTimeMillis();
			
			long d1 = t2-t1;
			long d2 = t3-t2;

			System.out.println("produtor levou: "+d1);
			System.out.println("consumidor levou: "+d2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void createThreads() {
		listaDePrimosGemeos = new ListaDePrimosGemeos();
		produtor = new ProdutorDePrimosGemeos(n + 1, listaDePrimosGemeos);
		consumidor = new CalculadorAlgoritmo3(n, listaDePrimosGemeos);
	}
}
