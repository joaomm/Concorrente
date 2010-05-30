package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import ep2.EspecificacoesDeMaquinas;
import ep2.ListaDePedidos;
import ep2.OfficeBoy;
import ep2.Pedido;

public class TestaOfficeBoy {
	private ListaDePedidos listaDePedidos;
	private EspecificacoesDeMaquinas especificacoes;

	@Before
	public void setUp() {
		especificacoes = new EspecificacoesDeMaquinas();
		especificacoes.adicionaMaquina(1);
		especificacoes.adicionaNaMaquina(1, 1, 100);
		especificacoes.adicionaNaMaquina(1, 2, 100);
		listaDePedidos = new ListaDePedidos();
	}

	@Test
	public void deveriaSerCriadoComEspecificacoesEConhecerAListaDePedidos() throws Exception {
		new OfficeBoy(especificacoes, listaDePedidos, null);
	}

	@Test
	public void deveriaReceberPrimeiroPedidoParaTrabalhar() throws Exception {
		OfficeBoy officeBoy = new OfficeBoy(especificacoes, listaDePedidos, null);
		int[] produtos = { 1 };
		int[] quantidades = { 100 };
		Pedido pedido = new Pedido(produtos, quantidades);
		listaDePedidos.adicionaPedido(pedido);
		officeBoy.recebePedidoParaTrabalhar();
		assertTrue(pedido.sendoProduzido());
	}

	@Test
	public void deveriaGerarConfiguracaoIdealPara1MaquinaEProduto1() throws Exception {
		OfficeBoy officeBoy = new OfficeBoy(especificacoes, listaDePedidos, null);
		int[] produtos = { 1 };
		int[] quantidades = { 100 };
		Pedido pedido = new Pedido(produtos, quantidades);
		listaDePedidos.adicionaPedido(pedido);
		officeBoy.recebePedidoParaTrabalhar();
		EspecificacoesDeMaquinas especificacaoParaMaquinas = officeBoy.geraConfiguracaoIdeal();
		assertEquals((Integer) 100, especificacaoParaMaquinas.daMaquina(1).get(1));

		assertNull(especificacaoParaMaquinas.daMaquina(1).get(2));
	}

	@Test
	public void deveriaGerarConfiguracaoIdealPara1MaquinaEProduto2() throws Exception {
		OfficeBoy officeBoy = new OfficeBoy(especificacoes, listaDePedidos, null);
		int[] produtos = { 2 };
		int[] quantidades = { 100 };
		Pedido pedido = new Pedido(produtos, quantidades);
		listaDePedidos.adicionaPedido(pedido);
		officeBoy.recebePedidoParaTrabalhar();
		EspecificacoesDeMaquinas especificacaoParaMaquinas = officeBoy.geraConfiguracaoIdeal();
		assertEquals((Integer) 100, especificacaoParaMaquinas.daMaquina(1).get(2));

		assertNull(especificacaoParaMaquinas.daMaquina(1).get(1));
	}

	@Test
	public void deveriaGerarConfiguracaoIdealPara1MaquinaE2Produtos() throws Exception {
		OfficeBoy officeBoy = new OfficeBoy(especificacoes, listaDePedidos, null);
		int[] produtos = { 1, 2 };
		int[] quantidades = { 100, 100 };
		Pedido pedido = new Pedido(produtos, quantidades);
		listaDePedidos.adicionaPedido(pedido);
		officeBoy.recebePedidoParaTrabalhar();
		EspecificacoesDeMaquinas especificacaoParaMaquinas = officeBoy.geraConfiguracaoIdeal();
		assertEquals((Integer) 100, especificacaoParaMaquinas.daMaquina(1).get(1));

		assertEquals((Integer) 100, especificacaoParaMaquinas.daMaquina(1).get(2));
	}

	@Test
	public void deveriaGerarConfiguracaoIdealPara1MaquinaE2ProdutosComQuantidadesDiferentesDe100()
			throws Exception {
		OfficeBoy officeBoy = new OfficeBoy(especificacoes, listaDePedidos, null);
		int[] produtos = { 1, 2 };
		int[] quantidades = { 200, 350 };
		Pedido pedido = new Pedido(produtos, quantidades);
		listaDePedidos.adicionaPedido(pedido);
		officeBoy.recebePedidoParaTrabalhar();
		EspecificacoesDeMaquinas especificacaoParaMaquinas = officeBoy.geraConfiguracaoIdeal();
		assertEquals((Integer) 200, especificacaoParaMaquinas.daMaquina(1).get(1));

		assertEquals((Integer) 350, especificacaoParaMaquinas.daMaquina(1).get(2));
	}

	@Test
	public void deveriaGerarConfiguracaoIdealPara2MaquinasComProdutosDiferentes() throws Exception {
		especificacoes.adicionaMaquina(2);
		especificacoes.adicionaNaMaquina(2, 3, 100);
		especificacoes.adicionaNaMaquina(2, 4, 100);

		OfficeBoy officeBoy = new OfficeBoy(especificacoes, listaDePedidos, null);
		int[] produtos = { 1, 3 };
		int[] quantidades = { 100, 100 };
		Pedido pedido = new Pedido(produtos, quantidades);
		listaDePedidos.adicionaPedido(pedido);
		officeBoy.recebePedidoParaTrabalhar();
		EspecificacoesDeMaquinas especificacaoParaMaquinas = officeBoy.geraConfiguracaoIdeal();
		assertEquals((Integer) 100, especificacaoParaMaquinas.daMaquina(1).get(1));

		assertEquals((Integer) 100, especificacaoParaMaquinas.daMaquina(2).get(3));
	}

	@Test
	public void deveriaGerarConfiguracaoIdealPara2MaquinasQueProduzemAMesmaQuantidadeDoMesmoProduto()
			throws Exception {
		especificacoes.adicionaMaquina(2);
		especificacoes.adicionaNaMaquina(2, 1, 100);

		OfficeBoy officeBoy = new OfficeBoy(especificacoes, listaDePedidos, null);
		int[] produtos = { 1 };
		int[] quantidades = { 100 };
		Pedido pedido = new Pedido(produtos, quantidades);
		listaDePedidos.adicionaPedido(pedido);
		officeBoy.recebePedidoParaTrabalhar();
		EspecificacoesDeMaquinas especificacaoParaMaquinas = officeBoy.geraConfiguracaoIdeal();
		assertEquals((Integer) 100, especificacaoParaMaquinas.daMaquina(2).get(1));
		assertNull(especificacaoParaMaquinas.daMaquina(1));
	}
}
