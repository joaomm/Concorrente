package ep2;

public class OfficeBoy {

	private final EspecificacoesDeMaquinas especificacoes;
	private final ListaDePedidos listaDePedidos;

	public OfficeBoy(EspecificacoesDeMaquinas especificacoes, ListaDePedidos listaDePedidos) {
		this.especificacoes = especificacoes;
		this.listaDePedidos = listaDePedidos;
	}

	public void recebePedidoParaTrabalhar() {
		Pedido pedido = listaDePedidos.proximoPedido();
		pedido.vaiParaProducao();
	}

	public EspecificacoesDeMaquinas geraConfiguracaoIdeal() {
		EspecificacoesDeMaquinas especificacaoParaMaquinas = new EspecificacoesDeMaquinas();

		especificacaoParaMaquinas.adicionaMaquina(1);
		especificacaoParaMaquinas.adicionaNaMaquina(1, 1, 100);
		especificacaoParaMaquinas.adicionaNaMaquina(1, 2, 100);

		return especificacaoParaMaquinas;
	}
}
