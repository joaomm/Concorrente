package ep2;

import java.util.HashMap;

import ep2.base.Maquina;

public class Servidor {
	public static void main(String[] args) {
		ParserDeArquivo parser = new ParserDeArquivo(args[0]);
		parser.executa();
		
		EspecificacoesDeMaquinas especificacoes = parser.getEspecificacoes();
		CriadorDeMaquinas criadorDeMaquinas = new CriadorDeMaquinas(especificacoes);
		HashMap<Integer, Maquina> maquinas = criadorDeMaquinas.crie();
	}

}
