package ep1.calculadores;

import java.math.BigInteger;

import ep1.ed.ListaDePrimos;

public class ProdutorDePrimos extends Thread {

	private ListaDePrimos lista;
	private final int n;
	private BigInteger two;

	public ProdutorDePrimos(int n, ListaDePrimos listaDePrimos) {
		this.n = n;
		lista = listaDePrimos;
		two = BigInteger.valueOf(2);
		lista.add(two);
		lista.add(BigInteger.valueOf(3));
	}

	@Override
	public void run() {
		for (int i = 1; i <= n; i++)
			lista.add(proximoPrimo());
	}

	private BigInteger proximoPrimo() {
		BigInteger ultimo = lista.getLast();
		for (BigInteger i = ultimo.add(two); true; i = i.add(two))
			if (ehPrimo(i))
				return i;
	}

	private boolean ehPrimo(BigInteger i) {
		return lista.ehPrimo(i);
	}
}
