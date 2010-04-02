package ep1;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Ep1 {
	public static void main(String[] args) {
		if (args.length == 2) {
			String algoritmoEscolhido = args[0];
			int n = Integer.parseInt(args[1]);
			if (algoritmoEscolhido.equals("1")) {
				rodaAlgoritmo1(n, 2);
			} else if (algoritmoEscolhido.equals("2")) {
				rodaAlgoritmo2(n, 2);
			} else {
				rodaAlgoritmo3(n, 2);
			}
		} else {
			System.out.println("O programa precisa de dois parametros!");
		}
	}

	private static void rodaAlgoritmo1(int n, int threads) {
		Algoritmo1 alg1 = new Algoritmo1(n, threads);

		long begin = System.currentTimeMillis();
		double sum = alg1.calculaSoma();
		long end = System.currentTimeMillis();

		long elapsedTime = end - begin;
		System.out.println("Resultado para " + n + " foi: " + sum + " em "
				+ elapsedTime + "ms");
	}

	private static void rodaAlgoritmo2(int n, int threads) {
		Algoritmo2 alg2 = new Algoritmo2(n, threads);

		long begin = System.currentTimeMillis();
		BigInteger sum = alg2.calculaSoma();
		long end = System.currentTimeMillis();

		long elapsedTime = end - begin;
		System.out.println("Resultado para " + n + " foi: " + sum + " em "
				+ elapsedTime + "ms");
	}

	private static void rodaAlgoritmo3(int n, int threads) {
		Algoritmo3 alg3 = new Algoritmo3(n, threads);

		long begin = System.currentTimeMillis();
		BigDecimal sum = alg3.calculaSoma();
		long end = System.currentTimeMillis();

		long elapsedTime = end - begin;
		System.out.println("Resultado para " + n + " foi: " + sum + " em "
				+ elapsedTime + "ms");
	}
}
