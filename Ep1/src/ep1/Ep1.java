package ep1;

public class Ep1 {
	public static void main(String[] args) {
		if (args.length == 2) {
			int n = Integer.parseInt(args[1]);
			Algoritmo algoritmoSelecionado = criaAlgoritmo(args[0]);
			algoritmoSelecionado.roda(n);
		}
		else 
			imprimeAjuda();
	}

	private static void imprimeAjuda() {
		System.out.println("O programa precisa de dois parametros:\n"
				+ "1) algoritmo escolhido [1, 2, 3].\n"
				+ "2) numero para o calculo da soma.\n");
	}

	private static Algoritmo criaAlgoritmo(String algoritmoEscolhido) {
		Algoritmo alg;
		if (algoritmoEscolhido.equals("1"))
			alg = new Algoritmo1(numeroDeCores());
		else if (algoritmoEscolhido.equals("2"))
			alg = new Algoritmo2(numeroDeCores());
		else
			alg = new Algoritmo3(numeroDeCores());
		return alg;
	}

	private static int numeroDeCores() {
		return 2;
	}
}
