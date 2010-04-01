package ep1.ed;

import java.math.BigInteger;
import java.util.ArrayList;

public class ListaDePrimos {

	private ArrayList<BigInteger> lista;
	private BigInteger zero;
	private BigInteger two;

	public ListaDePrimos() {
		zero = BigInteger.ZERO;
		lista = new ArrayList<BigInteger>();
		two = BigInteger.valueOf(2);

	}

	public void add(BigInteger item) {
		synchronized (lista) {
			lista.add(item);
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
		}
		return true;
	}

	public BigInteger get(int i) {
		try {
			synchronized (lista) {
				return lista.get(i);
			}
		} catch (IndexOutOfBoundsException e) {
			skip();
			return get(i);
		}
	}

	private void skip() {
		try {
			Thread.sleep(555);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}
}
