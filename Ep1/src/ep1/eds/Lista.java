package ep1.eds;

import java.util.ArrayList;

public class Lista<Item> {

	private ArrayList<Item> lista;

	
	public Lista() {
		lista = new ArrayList<Item>();
	}

	public int size() {
		return lista.size();
	}

	public void add(Item item) {
		synchronized (lista) {
			lista.add(item);
		}
	}

	public synchronized Item getLast() {
		return lista.get(size() - 1);
	}
	
	public Item get(int i) {
		esperaItemExistir(i);
		return lista.get(i);
	}

	private void esperaItemExistir(int i) {
		boolean itemNaoExiste = true;
		while (itemNaoExiste) {
			synchronized (lista) {
				itemNaoExiste = lista.size() <= i;
			}
			if (itemNaoExiste)
				skip();
		}
	}

	private void skip() {
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
