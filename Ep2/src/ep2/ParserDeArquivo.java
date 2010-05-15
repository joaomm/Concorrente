package ep2;


public class ParserDeArquivo {
	private EspecificacoesDeMaquinas especificacoes;
	private final String nomeDoArquivo;

	public ParserDeArquivo(String nomeDoArquivo) {
		this.nomeDoArquivo = nomeDoArquivo;
		especificacoes = new EspecificacoesDeMaquinas(); 
	}

	public EspecificacoesDeMaquinas getEspecificacoes() {
		return especificacoes;
	}
	
	public void executa(){
		LeitorDeArquivo leitorDeArquivo = new LeitorDeArquivo(nomeDoArquivo);
		String linha = leitorDeArquivo.proximaLinha();
		while(linha != null)
			parseia(linha);
	}

	public void parseia(String linha) {
		String[] valores = linha.split(";");
		Integer maquinaId = Integer.parseInt(valores[0]);
		Integer produto = Integer.parseInt(valores[1]);
		Integer tempo = Integer.parseInt(valores[2]);
		
		if(especificacoes.daMaquina(maquinaId) == null)
			especificacoes.adicionaMaquina(maquinaId);
		especificacoes.adicionaNaMaquina(maquinaId, produto, tempo);
	}
}
