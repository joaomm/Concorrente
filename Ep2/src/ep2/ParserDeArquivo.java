package ep2;

import java.util.HashMap;

public class ParserDeArquivo {
	private HashMap<Integer, HashMap<Integer, Integer>> mapa;

	public ParserDeArquivo(String nomeDoArquivo) {
		mapa = new HashMap<Integer, HashMap<Integer, Integer>>(); 
	}

	public HashMap<Integer, HashMap<Integer, Integer>> getMapa() {
		return mapa;
	}

	public void parseia(String linha) {
		String[] valores = linha.split(";");

		Integer maquinaId = Integer.parseInt(valores[0]);
		System.out.println(maquinaId);
		mapa.put(maquinaId, new HashMap<Integer, Integer>());
		mapa.get(maquinaId).put(1, 1);
	}
}
