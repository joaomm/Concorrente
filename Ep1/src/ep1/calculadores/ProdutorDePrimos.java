package ep1.calculadores;

import java.util.Vector;

import ep1.ed.Candidato;
import ep1.ed.ListaDePrimos;
import ep1.ed.MemoriaCompartilhada;

public class ProdutorDePrimos extends Thread {

	private static final int NAOENCONTRADO = -1;
	private static int numeroDeThreads = 0;
	private static int tamanhoIntervalo = 1000;

	private int id;
	private int fim;
	private int comeco;
	private int proximoAnalizado;
	private Vector<Candidato> candidatos;
	private final MemoriaCompartilhada compartilhada;

	public ProdutorDePrimos(MemoriaCompartilhada compartilhada) {
		this.compartilhada = compartilhada;
		this.id = ProdutorDePrimos.numeroDeThreads++;
		this.proximoAnalizado = 0;
		
		inicializaCandidatos();
	}

	private void inicializaCandidatos() {
		inicializaIntervalo();		
		inicializaVetorCandidatos();
		adiciona2ParaPrimeiraThread();
	}

	private void adiciona2ParaPrimeiraThread() {
		if(id == 0)
			candidatos.get(2).marcaQueEhPrimo();
	}

	private void inicializaIntervalo() {
		int tamanho = ProdutorDePrimos.tamanhoIntervalo;
		this.comeco = this.id * tamanho;
		this.fim = comeco + tamanho;
	}

	private void inicializaVetorCandidatos() {
		int tamanho = ProdutorDePrimos.tamanhoIntervalo;	
		candidatos = new Vector<Candidato>(tamanho);
		for (int i = 0; i < tamanho; i++) {
			candidatos.add(i, new Candidato(i + comeco));
		}
	}

	public void run() {
		while(compartilhada.naoEncontroTodosPrimos()) {
			marcaMultiplos();
			if (ehMinhaVezDeEncontrarPrimo()) 
				adicionaNovoPrimo();
		}
	}

	private void marcaMultiplos() {
		for (int i = proximoAnalizado; i < compartilhada.totalDePrimos(); i++) {
			int primo = compartilhada.getPrimo(i);
			marcaMultiplosDe(primo);
			proximoAnalizado++;
		}
	}

	private void marcaMultiplosDe(int primo) {
		int primeiroMultiplo = primo + primo;
		for (int i = primeiroMultiplo; i < fim; i += primo) {
			if (i >= comeco) {
				Candidato candidato = candidatos.get(i - comeco);
				candidato.marcaQueNaoEhPrimo();
			}
		}
	}

	private void adicionaNovoPrimo() {		
		int novoPrimo = encontraNovoPrimo();
		if (novoPrimo == NAOENCONTRADO) passaVez();
		else compartilhada.adicionaPrimo(novoPrimo);
	}

	private int encontraNovoPrimo() {
		int ultimoPrimo = compartilhada.ultimoPrimo();
		int i = ultimoPrimo + 1;
		if(ultimoPrimo < comeco) i = comeco; 
		
		for (; i < fim; i++) {
			Candidato candidato = candidatos.get(i - comeco);
			if (candidato.aindaNaoFoiMarcado())
				return candidato.getValor();
		}
		return NAOENCONTRADO;
	}

	private void passaVez() {
		compartilhada.trocaVez();
		reajustaIntervalo();
		inicializaVetorCandidatos();
		this.proximoAnalizado = 0;
	}


	private void reajustaIntervalo() {
		int tamanho = ProdutorDePrimos.tamanhoIntervalo;
		comeco += 2 * tamanho; 
		fim = comeco + tamanho; 
	}

	private boolean ehMinhaVezDeEncontrarPrimo() {
		return compartilhada.ehVezDo(id);
	}
}
