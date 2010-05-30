package ep2;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class EspecificacoesDeMaquinas {
	private HashMap<Integer, HashMap<Integer, Integer>> especificacoes;

	public EspecificacoesDeMaquinas() {
		especificacoes = new HashMap<Integer, HashMap<Integer, Integer>>();
	}

	public HashMap<Integer, Integer> daMaquina(int i) {
		return especificacoes.get(i);
	}

	public void adicionaMaquina(Integer maquinaId) {
		if (especificacoes.get(maquinaId) == null){
			HashMap<Integer, Integer> especificacaoVazia = new HashMap<Integer, Integer>();
			especificacoes.put(maquinaId, especificacaoVazia);
		}
	}

	public void adicionaNaMaquina(int maquinaId, int produto, int quantidade) {
		daMaquina(maquinaId).put(produto, quantidade);
	}

	public boolean vazias() {
		return especificacoes.isEmpty();
	}

	public List<Integer> maquinas() {
		return new LinkedList<Integer>(especificacoes.keySet());
	}
}
