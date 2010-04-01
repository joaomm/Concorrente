package ep1.calculadores;

import java.math.BigInteger;

import ep1.ed.ListaDePrimos;

public class ProdutorDePrimos extends Thread {

	private ListaDePrimos lista;
	private final int n;
	private BigInteger seis;
	private int qnt;

	public ProdutorDePrimos(int n, ListaDePrimos listaDePrimos) {
		this.n = n;
		lista = listaDePrimos;
		seis = BigInteger.valueOf(6);

		lista.add(BigInteger.valueOf(2));
		lista.add(BigInteger.valueOf(3));
		qnt = 0;
	}

	@Override
	public void run() {
		for (BigInteger i = seis; qnt < n; i = i.add(seis)) {
			checaEAdiciona(i.subtract(BigInteger.ONE));
			if (qnt < n) 
				checaEAdiciona(i.add(BigInteger.ONE));
		}
	}

	private void checaEAdiciona(BigInteger candidato) {
		if (ehPrimo(candidato)) {
			lista.add(candidato);
			qnt++;
		}
	}

	private boolean ehPrimo(BigInteger i) {
		return lista.ehPrimo(i);
	}
}
