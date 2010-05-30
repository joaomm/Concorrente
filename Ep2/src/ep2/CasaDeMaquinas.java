package ep2;

import java.util.HashMap;
import java.util.concurrent.Semaphore;

import ep2.base.Maquina;

public class CasaDeMaquinas {
	private Semaphore semaphore;
	private HashMap<Integer, Operario> operarios;

	public CasaDeMaquinas(EspecificacoesDeMaquinas especificacoes) {
		semaphore = new Semaphore(1, true);
		operarios = new HashMap<Integer, Operario>();
		criaOperariosComSuasMaquinas(especificacoes);
		
		inicializaOperarios();
	}
	
	private void criaOperariosComSuasMaquinas(EspecificacoesDeMaquinas especificacoes) {
		for (Integer maquinaID : especificacoes.maquinas()) {
			Maquina maquina = new Maquina(maquinaID, especificacoes.daMaquina(maquinaID));
			Operario operario = new Operario(maquina);
			operarios.put(maquinaID, operario);
			System.out.println("Criando maquina com id: " + maquinaID);
		}

	}
	
	private void inicializaOperarios() {
		for (Integer operarioID : operarios.keySet())
			operarios.get(operarioID).start();
	}

	public boolean consegueFazer(EspecificacoesDeMaquinas configuracaoIdeal) {
		for (Integer maquinaID : configuracaoIdeal.maquinas()) {
			if (operarios.get(maquinaID).ocupado())
				return false;
		}
		return true;
	}

	public void recebe(EspecificacoesDeMaquinas configuracaoIdeal, Pedido pedido) {
		for (Integer maquinaID : configuracaoIdeal.maquinas()) {
			operarios.get(maquinaID).recebeTrabalho(configuracaoIdeal.daMaquina(maquinaID), pedido);
		}
	}

	public synchronized void entra() {
		try {
			semaphore.acquire();
		} catch (InterruptedException e) {
			System.out.println("Matou um officeBoy que estava no semaforo da casa de maquinas...");
			e.printStackTrace();
		}
	}

	public void sai() {
		semaphore.release();
	}

	public void fecha() {
		for (Integer operarioID : operarios.keySet())
			operarios.get(operarioID).interrupt();
	}
}
