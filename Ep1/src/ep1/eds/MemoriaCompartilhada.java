package ep1.eds;


public class MemoriaCompartilhada {

	private final int totalDeThreads;
	private Lista<Long> primos;
	private Lista<Gemeos> gemeos;
	private int vez;
	private int n;
	private boolean podePararPorNumeroDePrimos;

	public MemoriaCompartilhada(int n, int threads,
			boolean podePararPorNumeroDePrimos) {
		this.podePararPorNumeroDePrimos = podePararPorNumeroDePrimos;
		this.n = n;
		totalDeThreads = threads;
		primos = new Lista<Long>();
		gemeos = new Lista<Gemeos>();
		
		inicializaPrimos();
		inicializaVez();
	}

	public MemoriaCompartilhada(int n, int totalDeThreads) {
		this(n, totalDeThreads, true);
	}

	private void inicializaVez() {
		this.vez = 0;
	}

	private void inicializaPrimos() {
		primos.add(Long.valueOf(2));
	}

	public int getN() {
		return n;
	}

	public int getTotalDeThreads() {
		return totalDeThreads;
	}

	public boolean ehVezDo(int threadId) {
		return threadId == vez;
	}

	public void trocaVez() {
		vez = (vez + 1) % totalDeThreads;
	}

	public int totalDePrimos() {
		return primos.size();
	}

	public void adicionaPrimo(int novoPrimo) {
		primos.add(Long.valueOf(novoPrimo));
	}

	public long ultimoPrimo() {
		return primos.getLast();
	}

	public long getPrimo(int i) {
		return primos.get(i);
	}

	public boolean encontrouTodosPrimos() {
		if (podePararPorNumeroDePrimos)
			return primos.size() > n;
		else
			return gerouTodosGemeos();
	}

	public Gemeos getGemeos(int i) {
		return gemeos.get(i);
	}

	public void addGemeos(long primo1, long primo2) {
		gemeos.add(new Gemeos(primo1, primo2));
	}

	public boolean gerouTodosGemeos() {
		return gemeos.size() == n;
	}

}
