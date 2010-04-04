package ep1;

public abstract class Algoritmo {
	protected String nome;
	protected int n;
	protected Number resultado;

	public Number calculaSoma(int n) {
		this.n = n;
		createThreads();
		startThreads();
		joinThreads();	
		return resultado;
	}

	protected abstract void createThreads();
	protected abstract void startThreads();
	protected abstract void joinThreads();

	public void roda(int n) {
		long begin = System.currentTimeMillis();
		Number sum = calculaSoma(n);
		long end = System.currentTimeMillis();

		long elapsedTime = end - begin;
		
		System.out.println(nome);
		System.out.println("  n: " + n);
		System.out.println("  resultado: " + sum);
		System.out.println("  tempo gasto: " + elapsedTime + "ms");
	}
}
