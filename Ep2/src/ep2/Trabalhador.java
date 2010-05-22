package ep2;

import java.util.HashMap;

import ep2.base.Maquina;

public class Trabalhador {

	private final EspecificacoesDeMaquinas especificacoes;
	private final ListaDePedidos listaDePedidos;

	public Trabalhador(Maquina maquina, EspecificacoesDeMaquinas especificacoes, ListaDePedidos listaDePedidos) {
		this.especificacoes = especificacoes;
		this.listaDePedidos = listaDePedidos;
	}

	public Pedido escolheEmQuePedidoTrabalhar() {
		HashMap<Integer,Integer> especificacao = especificacoes.daMaquina(1);
				
		for (int i = 0; i < 2; i++) {
			if(especificacao.containsKey(i))
				return listaDePedidos.acessaPedido(1);		
		}
		return null;
	}

}
