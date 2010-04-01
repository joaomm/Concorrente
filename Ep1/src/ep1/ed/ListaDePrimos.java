package ep1.ed;

import java.math.BigInteger;
import java.util.ArrayList;

public class ListaDePrimos {

	private ArrayList<BigInteger> lista;
	private ArrayList<BigInteger> listaMult2;
	private BigInteger zero;
	private BigInteger dois;

	public ListaDePrimos() {
		zero = BigInteger.ZERO;
		dois = BigInteger.valueOf(2);

		lista = new ArrayList<BigInteger>();
		listaMult2 = new ArrayList<BigInteger>();

	}

	public void add(BigInteger item) {
		synchronized (lista) {
			lista.add(item);
			listaMult2.add(item.multiply(dois));
		}
	}

	public BigInteger getLast() {
		return lista.get(lista.size() - 1);
	}

	public boolean ehPrimo(BigInteger candidato) {		
		for (int i = 0; i < lista.size(); i++) {
			BigInteger primo = lista.get(i);
			if (candidato.mod(primo).equals(zero)) {
				return false;
			}
			if(listaMult2.get(i).compareTo(candidato)> 0)break;
		}
		return true;
	}

	public BigInteger get(int i) {
		boolean cond = true;
		while (cond) {
			synchronized (lista) {
				cond = lista.size() <= i;
			}
			if (cond) skip();
		}
		return lista.get(i);
	}

	private void skip() {
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
