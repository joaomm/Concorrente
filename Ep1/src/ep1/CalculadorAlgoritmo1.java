package ep1;


public class CalculadorAlgoritmo1 extends Thread {
	private double soma;
	private final int end;
	private final int begin;

	public CalculadorAlgoritmo1(int begin, int end) {
		this.begin = begin;
		this.end = end;
	}

	@Override
	public void run() {
		soma = 0;
		double one = 1;
		double quadrado; 
		for (long i = begin; i <= end; i++) {
			quadrado = i;
			quadrado = quadrado * quadrado;
			soma = soma + (one / quadrado);
		}
	}

	public double getSoma() {
		return soma;
	}
}
