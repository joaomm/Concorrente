package ep1.ed;

import java.math.BigInteger;
import java.util.ArrayList;

public class ListaDePrimosGemeos {

	private ArrayList<Gemeos> listaPrimosGemeos;

	public ListaDePrimosGemeos() {
		listaPrimosGemeos = new ArrayList<Gemeos>();
	}

	public void add(BigInteger primo1, BigInteger primo2) {
		synchronized (listaPrimosGemeos) {
			listaPrimosGemeos.add(new Gemeos(primo1, primo2));
		}
	}

	public Gemeos getGemeos(int i) {
		boolean cond = true;
		while (cond) {
			synchronized (listaPrimosGemeos) {
				cond = listaPrimosGemeos.size() <= i;
			}
			if (cond)
				skip();
		}
		return listaPrimosGemeos.get(i);
	}

	protected void skip() {
		try {
			Thread.sleep(250);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
