package ep1.ed;


public class Candidato {

	private static final int NAO = -1;
	private static final int TALVEZ = 0;
	private static final int SIM = 1;
	private final int valor;
	private int ehPrimo;

	public Candidato(int valor) {
		this.valor = valor;
		this.ehPrimo = TALVEZ;
	}

	public int getValor() {
		return valor;
	}

	public void marcaQueNaoEhPrimo() {
		ehPrimo = NAO;
	}

	public void marcaQueEhPrimo() {
		ehPrimo = SIM;
	}

	public boolean aindaNaoFoiMarcado() {
		return ehPrimo == TALVEZ;	
	}
}
