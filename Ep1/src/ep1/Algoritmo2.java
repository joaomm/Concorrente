package ep1;

import java.math.BigInteger;
import java.util.Vector;

import ep1.calculadores.CalculadorAlgoritmo2;
import ep1.calculadores.ProdutorDePrimos;
import ep1.ed.Candidato;
import ep1.ed.ListaDePrimos;

public class Algoritmo2 {
	private final int n;
	private ProdutorDePrimos produtor;
	private CalculadorAlgoritmo2 consumidor;
	private ProdutorDePrimos produtor2;
	private ListaDePrimos listaDePrimos;
	private Vector<Candidato> candidatos;

	public Algoritmo2(int n, int threads) {
		this.n = n;
	}

	public BigInteger calculaSoma() {
		createThreads();
		startThreads();
		return joinThreads();
	}

	private BigInteger joinThreads() {
		try {
			consumidor.join();
			return consumidor.getSoma();
		} catch (InterruptedException e) {
			e.printStackTrace();
			return BigInteger.ZERO;
		}
	}

	private void startThreads() {
		produtor.start();
		produtor2.start();				
		consumidor.start();
	}

	private void createThreads() {
		listaDePrimos = new ListaDePrimos();
		
		criaCandidatos();	
		inserePrimeirosPrimos();
		
		produtor = new ProdutorDePrimos(n+1, listaDePrimos, candidatos);
		produtor2 = new ProdutorDePrimos(n+1, listaDePrimos, candidatos);
		
		consumidor = new CalculadorAlgoritmo2(n, listaDePrimos);
	}

	private void inserePrimeirosPrimos() {
		candidatos.get(2).marcaQueEhPrimo();
		listaDePrimos.add(2);
		candidatos.get(3).marcaQueEhPrimo();
		listaDePrimos.add(3);
	}

	private void criaCandidatos() {
		int capacidade = (n+1)*100;
		candidatos = new Vector<Candidato>(capacidade);
		for (int i = 0; i < capacidade; i++) {
			candidatos.add(i, new Candidato(i));
		}
	}
}
