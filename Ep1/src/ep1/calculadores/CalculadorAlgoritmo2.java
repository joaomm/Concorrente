package ep1.calculadores;

import java.math.BigInteger;

import ep1.ed.ListaDePrimos;

public class CalculadorAlgoritmo2 extends Thread {

	private final int n;
	private final ListaDePrimos lista;
	private BigInteger soma;

	public CalculadorAlgoritmo2(int n, ListaDePrimos listaDePrimos) {
		this.n = n;
		lista = listaDePrimos;
		soma = BigInteger.ZERO;
	}

	@Override
	public void run() {
		for (int i = 0; i < n - 1; i++) {
			soma = soma.add(termo(i));
		}
	}

	private BigInteger termo(int i) {
		return e(i).multiply(e(i + 1));
	}

	private BigInteger e(int i) {
		return primo(i).add(primo(i + 1));
	}

	private BigInteger primo(int i) {
		return BigInteger.valueOf(lista.get(i));
	}

	public BigInteger getSoma() {
		return soma;
	}

}
