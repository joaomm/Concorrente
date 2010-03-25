package ep1;

import java.util.LinkedList;

public class Algoritmo1 {

	private static LinkedList<CalculadorAlgoritmo1> listaDeThreads;
	private final int n;
	private int threads;

	public Algoritmo1(int n, int threads) {
		this.n = n;
		this.threads = threads;
	}

	public double calculaSoma() {
		createThreads();
		startThreads();
		return joinThreads();
	}

	private double joinThreads() {
		double soma = 0.0;
		try {
			for (CalculadorAlgoritmo1 calculador : listaDeThreads) {
				calculador.join();
				soma += calculador.getSoma();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return soma;
	}

	private void startThreads() {
		for (CalculadorAlgoritmo1 calculador : listaDeThreads) {
			calculador.start();
		}
	}

	private void createThreads() {
		int begin = 1;
		int step = n / threads;
		int end = step;

		listaDeThreads = new LinkedList<CalculadorAlgoritmo1>();

		for (int i = 0; i < threads; i++) {
			listaDeThreads.add(new CalculadorAlgoritmo1(begin, end));
			begin = end + 1;
			end = end + step;
			if (i == threads - 2 && end != n) {
				end = n;
			}
		}
	}
}
