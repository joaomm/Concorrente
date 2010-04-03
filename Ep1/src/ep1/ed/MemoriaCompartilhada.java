package ep1.ed;

public class MemoriaCompartilhada {

	private ListaDePrimos primos;
	private int vez;
	private final int totalDeThreads;
	private int n;

	public MemoriaCompartilhada(int n, int totalDeThreads, ListaDePrimos listaDePrimos) {
		this.n = n;
		this.totalDeThreads = totalDeThreads;
		this.primos = listaDePrimos;
		inicializaPrimos();
		inicializaVez();
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

	public boolean naoEncontrouTodosPrimos() {
		return primos.size() < n;
	}

	public int getTotalDeThreads() {
		return totalDeThreads;
	}

}
