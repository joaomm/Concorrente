package ep1;

import java.util.HashMap;
import java.util.LinkedList;

public class Ep1 {

	private static HashMap<Integer, Integer> tempoLimitePara;

	public static void main(String[] args) {
		tempoLimitePara = new HashMap<Integer, Integer>();
		tempoLimitePara.put(1, 100000);
		tempoLimitePara.put(2, 10000);
		tempoLimitePara.put(3, 500);
		
		if (args.length == 2) {
			int n = Integer.parseInt(args[1]);
			Algoritmo algoritmoSelecionado = criaAlgoritmo(args[0], n);
			algoritmoSelecionado.roda(n);
		} else
			imprimeAjuda();
	}

	private static void imprimeAjuda() {
		System.out.println("O programa precisa de dois parametros:\n"
				+ "1) algoritmo escolhido [1, 2, 3].\n"
				+ "2) numero para o calculo da soma.\n");
	}

	private static Algoritmo criaAlgoritmo(String algoritmoEscolhido, int n) {
		Algoritmo alg;
		if (algoritmoEscolhido.equals("1"))
			alg = new Algoritmo1(numeroDeCores(n, 1));
		else if (algoritmoEscolhido.equals("2"))
			alg = new Algoritmo2(numeroDeCores(n, 2));
		else
			alg = new Algoritmo3(numeroDeCores(n, 3));
		return alg;
	}

	private static int numeroDeCores(int n, int algoritmoEscolhido) {
		LinkedList<Long> listaDeTemposDeTeste = new LinkedList<Long>();
		listaDeTemposDeTeste.add(Long.valueOf(0));

		if (n <= tempoLimitePara.get(algoritmoEscolhido))
			return 1;

		for (int i = 1; true; i++) {
			long tempoGasto = calculaTempo(i);

			System.out.println("tempo do teste de " + i + " cores: "
					+ tempoGasto);
			if (listaDeTemposDeTeste.getLast() * 1.7 > tempoGasto)
				break;
			listaDeTemposDeTeste.add(tempoGasto);
		}
		
		int numeroDeThreadsIdeal = listaDeTemposDeTeste.size();
		System.out.println("Decidi que é melhor rodar com "
				+ numeroDeThreadsIdeal + " threads.");
		return numeroDeThreadsIdeal;
	}

	private static long calculaTempo(int i) {
		long t1 = System.currentTimeMillis();
		joinTestes(runTestes(i));
		long t2 = System.currentTimeMillis();
		long tempoGasto = t2 - t1;
		return tempoGasto;
	}

	private static void joinTestes(LinkedList<Teste> listaDeTestes) {
		try {
			for (Teste teste : listaDeTestes)
				teste.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static LinkedList<Teste> runTestes(int n) {
		LinkedList<Teste> lista = new LinkedList<Teste>();
		for (int i = 0; i < n; i++) {
			Teste teste = new Teste(n);
			lista.add(teste);
			teste.run();
		}
		return lista;
	}
}
