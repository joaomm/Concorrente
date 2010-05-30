package ep2;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class OfficeBoy extends Thread {

	private final EspecificacoesDeMaquinas especificacoes;
	private final ListaDePedidos listaDePedidos;
	private Pedido pedido;
	private boolean repassouOTrabalho;
	private CasaDeMaquinas casaDeMaquinas;

	public OfficeBoy(EspecificacoesDeMaquinas especificacoes, ListaDePedidos listaDePedidos,
			CasaDeMaquinas casaDeMaquinas) {
		this.especificacoes = especificacoes;
		this.listaDePedidos = listaDePedidos;
		this.casaDeMaquinas = casaDeMaquinas;
	}

	@Override
	public void run() {
		while (true) {
			recebePedidoParaTrabalhar();
			EspecificacoesDeMaquinas configuracaoIdeal = geraConfiguracaoIdeal();
			repassouOTrabalho = false;
			while (!repassouOTrabalho) {
				entraNaCasaDeMaquinas();
				System.out.println("OfficeBoy " + getId() + " entrou na Secao Critica");
				if (casaDeMaquinas.consegueFazer(configuracaoIdeal)) {
					repassaTrabalho(configuracaoIdeal);
					saiDaCasaDeMaquinas();
					System.out.println("\tOfficeBoy " + getId()
							+ " repassou trabalho e saiu da S.C.");
				} else {
					saiDaCasaDeMaquinas();
					System.out.println("\tOfficeBoy " + getId()
							+ " NAO repassou trabalho e saiu da S.C.");
					skip();
				}
			}
		}
	}

	private void entraNaCasaDeMaquinas() {
		casaDeMaquinas.entra();
	}

	private void repassaTrabalho(EspecificacoesDeMaquinas configuracaoIdeal) {
		repassouOTrabalho = true;
		casaDeMaquinas.recebe(configuracaoIdeal, pedido);
	}

	private void saiDaCasaDeMaquinas() {
		casaDeMaquinas.sai();
	}

	public void recebePedidoParaTrabalhar() {
		pedido = listaDePedidos.proximoPedido();
		while (pedido == null) {
			skip();
			pedido = listaDePedidos.proximoPedido();
		}
		System.out.println("Officeboy " + this.getId() + " pegou pedido " + pedido.id());
		pedido.vaiParaProducao();
	}

	public EspecificacoesDeMaquinas geraConfiguracaoIdeal() {
		EspecificacoesDeMaquinas especificacaoParaMaquinas = new EspecificacoesDeMaquinas();
		HashMap<Integer, Integer> produtoEQuantidades = pedido.produtoEQuantidades();

		List<Integer> maquinas = especificacoes.maquinas();

		for (Integer produto : produtoEQuantidades.keySet()) {
			Integer quantidade = produtoEQuantidades.get(produto);

			LinkedList<Integer> maquinasQueProduzemOProduto = selecionaMaquinasQueProduzemOProduto(
					maquinas, produto);

			int idMenorMaquina = selecionaMaquinaComMenosProdutos(maquinasQueProduzemOProduto);

			especificacaoParaMaquinas.adicionaMaquina(idMenorMaquina);
			especificacaoParaMaquinas.adicionaNaMaquina(idMenorMaquina, produto, quantidade);
		}

		return especificacaoParaMaquinas;
	}

	private int selecionaMaquinaComMenosProdutos(LinkedList<Integer> maquinasQueProduzemOProduto) {
		int idMenorMaquina = maquinasQueProduzemOProduto.getFirst();
		for (Integer maquinaID : maquinasQueProduzemOProduto)
			if (especificacoes.daMaquina(idMenorMaquina).size() > especificacoes.daMaquina(
					maquinaID).size())
				idMenorMaquina = maquinaID;
		return idMenorMaquina;
	}

	private LinkedList<Integer> selecionaMaquinasQueProduzemOProduto(List<Integer> maquinas,
			Integer produto) {
		LinkedList<Integer> maquinasQueProduzemOProduto = new LinkedList<Integer>();
		for (Integer maquinaID : maquinas) {
			if (especificacoes.daMaquina(maquinaID) != null
					&& especificacoes.daMaquina(maquinaID).get(produto) != null)
				maquinasQueProduzemOProduto.add(maquinaID);
		}
		return maquinasQueProduzemOProduto;
	}

	private void skip() {
		try {
			sleep(1000);
		} catch (InterruptedException e) {
			System.out.println("Office Boy morto: " + getId());
			System.exit(0);
		}
	}
}
