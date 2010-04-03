package ep1.ed;

import java.util.ArrayList;

public class ListaDePrimos {

	private ArrayList<Long> lista;

	public ListaDePrimos() {
		lista = new ArrayList<Long>();
	}

	public void add(long item) {
		synchronized (lista) {
			lista.add(item);
		}
	}

	public long get(int i) {
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

	public synchronized long getLast() {
		return lista.get(size() - 1);		
	}

	public int size() {
		synchronized (lista) {
			return lista.size();		
		}
	}
}
