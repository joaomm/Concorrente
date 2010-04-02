package ep1.ed;

import java.math.BigInteger;
import java.util.ArrayList;

public class ListaDePrimos {

	private ArrayList<BigInteger> lista;
	private BigInteger zero;

	public ListaDePrimos() {
		zero = BigInteger.ZERO;

		lista = new ArrayList<BigInteger>();
	}

	public void add(BigInteger item) {
		synchronized (lista) {
			lista.add(item);
		}
	}

	public boolean ehPrimo(BigInteger candidato) {		
		int n = (lista.size() / 2)+1;
		for (int i = 0; i < n; i++) {
			BigInteger primo = lista.get(i);
			if (candidato.mod(primo).equals(zero)) {
				return false;
			}
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

	protected void skip() {
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
