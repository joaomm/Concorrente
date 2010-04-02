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

			TestadorDePrimalidade teste1 = new TestadorDePrimalidade(primo1,
					listaDePrimos);
			TestadorDePrimalidade teste2 = new TestadorDePrimalidade(primo2,
					listaDePrimos);
			teste1.start();
			teste2.start();

			try {
				teste1.join();
				teste2.join();
				if (teste1.ehPrimo()) {
					listaDePrimos.add(primo1);
				}
				if (teste2.ehPrimo()) {
					listaDePrimos.add(primo1);
				}
				if (teste1.ehPrimo() && teste2.ehPrimo()) {
					qnt++;
					listaDeGemeos.add(primo1, primo2);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
