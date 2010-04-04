package ep1;

import java.util.LinkedList;

import ep1.calculadores.CalculadorAlgoritmo1;

public class Algoritmo1 extends Algoritmo{

	private static LinkedList<CalculadorAlgoritmo1> listaDeThreads;
	private int threads;

	public Algoritmo1(int threads) {
		nome = "Algoritmo 1";
		this.threads = threads;
	}

	protected void joinThreads() {
		double soma = 0.0;
		try {
			for (CalculadorAlgoritmo1 calculador : listaDeThreads) {
				calculador.join();
				soma += calculador.getSoma();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		resultado = soma;
	}

	protected void startThreads() {
		for (CalculadorAlgoritmo1 calculador : listaDeThreads) {
			calculador.start();
		}
	}

	protected void createThreads() {
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
