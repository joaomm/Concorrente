package ep2;

import java.util.HashMap;

import ep2.base.Maquina;

public class CriadorDeMaquinas {

	private final EspecificacoesDeMaquinas especificacoes;

	public CriadorDeMaquinas(EspecificacoesDeMaquinas especificacoes) {
		this.especificacoes = especificacoes;
	}

	public HashMap<Integer, Maquina> crie() {
		HashMap<Integer, Maquina> mapa = new HashMap<Integer, Maquina>();
		for (int maquinaId : especificacoes.maquinas()) {
			mapa.put(maquinaId, new Maquina(maquinaId, especificacoes.daMaquina(maquinaId)));
		}
		return mapa;
	}

}
