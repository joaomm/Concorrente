package ep1.calculadores;

import java.util.Vector;

import ep1.ed.Candidato;
import ep1.ed.ListaDePrimos;

public class ProdutorDePrimos extends Thread {

	private static int numeroDeThreads = 1;
	private final ListaDePrimos listaDePrimos;
	private final Vector<Candidato> listaDeCandidatos;
	private final int n;
	private int id;

	public ProdutorDePrimos(int n, ListaDePrimos listaDePrimos,
			Vector<Candidato> listaDeCandidatos) {
		this.n = n;
		this.listaDePrimos = listaDePrimos;
		this.listaDeCandidatos = listaDeCandidatos;
		this.id = ProdutorDePrimos.numeroDeThreads++;
	}

	@Override
	public void run() {
		for (int i = 1; listaDePrimos.size() < n; i++) {
			int ultimoPrimo = listaDePrimos.getLast();
			int primo = primoDepoisDe(ultimoPrimo);
			
			marcaPrimoEncontrado(primo);
			marcaMultiplosDe(primo);
		}		
	}

	private void marcaPrimoEncontrado(int primo) {
		Candidato candidato = listaDeCandidatos.get(primo);
		candidato.marcaQueEhPrimo();
		listaDePrimos.add(primo);
	}

	private void marcaMultiplosDe(int primo) {
		for (int i = primo + primo; i < listaDeCandidatos.size(); i += primo) {
			Candidato candidato = listaDeCandidatos.get(i);
			candidato.marcaQueNaoEhPrimo();
		}
	}

	private int primoDepoisDe(int ultimoPrimo) {
		for (int i = ultimoPrimo + 2; i < listaDeCandidatos.size(); i += 2) {
			Candidato candidato = listaDeCandidatos.get(i);
			if (candidato.ehPrimo() == Candidato.TALVEZ) {
				return candidato.getValor();
			}
		}
		return 0;
	}
}
