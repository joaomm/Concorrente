package ep1.ed;

public class MemoriaCompartilhada {

	private ListaDePrimos primos;
	private int vez;
	private final int n;

	public MemoriaCompartilhada(int n, ListaDePrimos listaDePrimos) {
		this.n = n;
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
		vez = (vez + 1) % 2;
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

	public int getPrimo(int i) {
		return primos.get(i);
	}

	public int ultimoPrimo() {
		return primos.getLast();
	}

	public boolean naoEncontroTodosPrimos() {
		return primos.size() < n;
	}

}
