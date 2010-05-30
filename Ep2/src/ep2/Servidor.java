package ep2;

public class Servidor {
	private static ListaDePedidos listaDePedidos;
	private static CasaDeMaquinas casaDeMaquinas;
	private static EspecificacoesDeMaquinas especificacoes;
	private static int numeroDeCores = 1;

	public static void main(String[] args) {
		ParserDeArquivo parser = new ParserDeArquivo(args[0]);
		parser.executa();

		especificacoes = parser.getEspecificacoes();
		casaDeMaquinas = new CasaDeMaquinas(especificacoes);

		listaDePedidos = new ListaDePedidos();
		criaEInicializaOfficeBoys();

		ligaWS();
	}

	private static void criaEInicializaOfficeBoys() {
		for (int i = 0; i < numeroDeCores; i++) {
			OfficeBoy officeBoy = new OfficeBoy(especificacoes, listaDePedidos, casaDeMaquinas);
			officeBoy.start();
		}
	}

	private static void ligaWS() {
		// TODO Auto-generated method stub

	}
}
