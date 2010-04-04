package ep1;

public class Teste extends Thread {
	private static final int N_PARA_TESTES = 1000000;
	private final int id;

	public Teste(int i) {
		id = i;
	}

	@Override
	public void run() {
		for (int i = 0; i < N_PARA_TESTES; i++) {
			double teste = i * 15.5;
			teste *= id * 2;
		}
	}
}
