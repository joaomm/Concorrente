package ep1.produtores;

import ep1.ed.MemoriaCompartilhada;

public class ProdutorDePrimosGemeos extends Thread {

	private final MemoriaCompartilhada compartilhada;

	public ProdutorDePrimosGemeos(MemoriaCompartilhada compartilhada) {
		this.compartilhada = compartilhada;
	}

	@Override
	public void run() {
		for (int i = 0; !compartilhada.gerouTodosGemeos(); i++) {
			long primo1 = compartilhada.getPrimo(i);
			long primo2 = compartilhada.getPrimo(i + 1);
			if (primo2 - primo1 == 2)
				compartilhada.addGemeos(primo1, primo2);
		}
	}
}