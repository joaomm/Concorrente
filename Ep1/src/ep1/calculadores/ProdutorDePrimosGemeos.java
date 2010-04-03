package ep1.calculadores;

import java.math.BigInteger;

import ep1.ed.ListaDePrimos;
import ep1.ed.ListaDePrimosGemeos;

public class ProdutorDePrimosGemeos extends Thread {

	private final int n;
	private final ListaDePrimosGemeos listaDeGemeos;
	private BigInteger seis;
	private ListaDePrimos listaDePrimos;
	private int qnt;

	public ProdutorDePrimosGemeos(int n, ListaDePrimosGemeos listaDeGemeos) {
		this.n = n;
		seis = BigInteger.valueOf(6);

		listaDePrimos = new ListaDePrimos();
		listaDePrimos.add(BigInteger.valueOf(2));
		listaDePrimos.add(BigInteger.valueOf(3));

		this.listaDeGemeos = listaDeGemeos;
		listaDeGemeos.add(BigInteger.valueOf(3), BigInteger.valueOf(5));
		qnt = 0;
	}

	@Override
	public void run() {
		for (BigInteger i = seis; qnt < n; i = i.add(seis)) {
			BigInteger primo1 = i.subtract(BigInteger.ONE);
			BigInteger primo2 = i.add(BigInteger.ONE);

			boolean ehPrimo1 = testaEAdiciona(primo1);
			boolean ehPrimo2 = testaEAdiciona(primo2);

			if (ehPrimo1 && ehPrimo2) {
				qnt++;
				listaDeGemeos.add(primo1, primo2);
			}
		}
	}

	private boolean testaEAdiciona(BigInteger primo) {
		boolean ehPrimo = listaDePrimos.ehPrimo(primo);
		if (ehPrimo)
			listaDePrimos.add(primo);
		return ehPrimo;
	}
}
