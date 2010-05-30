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
		criaOperadoresEComSuasMaquinas(especificacoes);
	}

	private void criaOperadoresEComSuasMaquinas(EspecificacoesDeMaquinas especificacoes) {
		for (Integer maquinaID : especificacoes.maquinas()) {
			Maquina maquina = new Maquina(maquinaID, especificacoes.daMaquina(maquinaID));
			Operario operario = new Operario(maquina);
			operarios.put(maquinaID, operario);
		}
		
	}

	public boolean consegueFazer(EspecificacoesDeMaquinas configuracaoIdeal) {
		for (Integer maquinaID : configuracaoIdeal.maquinas()) {
			if (operarios.get(maquinaID).ocupado())
				return false;
		}
		return true;
	}

	public void recebe(EspecificacoesDeMaquinas configuracaoIdeal) {
		for (Integer maquinaID : configuracaoIdeal.maquinas()) {
			operarios.get(maquinaID).recebeTrabalho(configuracaoIdeal.daMaquina(maquinaID));
		}
	}
	
	public synchronized void entra() {
		try {
			semaphore.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void sai() {
		semaphore.release();
	}
}
