package ep1.calculadores;


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
		for (long i = begin; i <= end; i++) {
			double quadrado = i * i;
			soma = soma + (1 / quadrado);
		}
	}

	public double getSoma() {
		return soma;
	}
}
