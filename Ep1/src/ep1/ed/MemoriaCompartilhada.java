package ep1.ed;

import java.util.ArrayList;

public class MemoriaCompartilhada {

	private ListaDePrimos primos;
	private int vez;
	private final int totalDeThreads;
	private int n;
	private ArrayList<Gemeos> listaDeGemeos;
	private boolean podePararPorNumeroDePrimos;

	public MemoriaCompartilhada(int n, int totalDeThreads,
			ListaDePrimos listaDePrimos, boolean podePararPorNumeroDePrimos) {
		this.podePararPorNumeroDePrimos = podePararPorNumeroDePrimos;
		this.n = n;
		this.totalDeThreads = totalDeThreads;
		this.primos = listaDePrimos;
		inicializaPrimos();
		inicializaVez();
		listaDeGemeos = new ArrayList<Gemeos>();
	}

	public MemoriaCompartilhada(int n, int totalDeThreads,
			ListaDePrimos listaDePrimos) {
		this(n, totalDeThreads, listaDePrimos, true);
	}

	private void inicializaVez() {
		this.vez = 0;
	}

	private void inicializaPrimos() {
		primos.add(2);
	}

	public boolean ehVezDo(int threadId) {
		return threadId == vez;
	}

	public void trocaVez() {
		vez = (vez + 1) % totalDeThreads;
	}

	public ListaDePrimos getPrimos() {
		return primos;
	}

	public int totalDePrimos() {
		return primos.size();
	}

	public void adicionaPrimo(int novoPrimo) {
		primos.add(novoPrimo);
	}

	public long getPrimo(int i) {
		return primos.get(i);
	}

	public long ultimoPrimo() {
		return primos.getLast();
	}

	public boolean gerouTodosGemeos() {
		return listaDeGemeos.size() == n;
	}

	public boolean naoEncontrouTodosPrimos() {
		if (podePararPorNumeroDePrimos)
			return primos.size() < n;
		else
			return ! gerouTodosGemeos();
	}

	public int getTotalDeThreads() {
		return totalDeThreads;
	}
	
	public Gemeos getGemeos(int i) {
		boolean cond = true;
		while (cond) {
			synchronized (listaDeGemeos) {
				cond = listaDeGemeos.size() <= i;
			}
			if (cond)
				skip();
		}
		return listaDeGemeos.get(i);
	}

	protected void skip() {
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void addGemeos(long primo1, long primo2) {
		listaDeGemeos.add(new Gemeos(primo1, primo2));
	}

	public int getN() {
		return n;
	}

}
