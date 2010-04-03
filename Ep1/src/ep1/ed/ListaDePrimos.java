package ep1.ed;

import java.util.ArrayList;

public class ListaDePrimos {

	private ArrayList<Integer> lista;

	public ListaDePrimos() {
		lista = new ArrayList<Integer>();
	}

	public void add(int item) {
		synchronized (lista) {
			lista.add(item);
		}
	}

	public int get(int i) {
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

	public synchronized int getLast() {
		return lista.get(size() - 1);		
	}

	public int size() {
		synchronized (lista) {
			return lista.size();		
		}
	}
}
