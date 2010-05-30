package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
	public void setUp(){
		especificacoes = new EspecificacoesDeMaquinas();
		especificacoes.adicionaMaquina(1);
		especificacoes.adicionaNaMaquina(1, 1, 100);
		especificacoes.adicionaNaMaquina(1, 2, 100);
		listaDePedidos = new ListaDePedidos();
	}
	
	@Test
	public void deveriaSerCriadoComEspecificacoesEConhecerAListaDePedidos() throws Exception {
		new OfficeBoy(especificacoes, listaDePedidos);
	}
	
	@Test
	public void deveriaReceberPrimeiroPedidoParaTrabalhar() throws Exception {
		OfficeBoy officeBoy = new OfficeBoy(especificacoes, listaDePedidos);
		int[] produtos = {1};
		int[] quantidades = {100};
		Pedido pedido = new Pedido(produtos, quantidades);
		listaDePedidos.adicionaPedido(pedido);		
		officeBoy.recebePedidoParaTrabalhar();
		assertTrue(pedido.sendoProduzido());
	}
	
	@Test
	public void deveriaGerarConfiguracaoIdealPara1MaquinaE1Produto() throws Exception {
		OfficeBoy officeBoy = new OfficeBoy(especificacoes, listaDePedidos);
		int[] produtos = {1};
		int[] quantidades = {100};
		Pedido pedido = new Pedido(produtos, quantidades);
		listaDePedidos.adicionaPedido(pedido);
		officeBoy.recebePedidoParaTrabalhar();
		EspecificacoesDeMaquinas especificacaoParaMaquinas = officeBoy.geraConfiguracaoIdeal();
		assertFalse(especificacaoParaMaquinas.daMaquina(1).isEmpty());
		assertEquals((Integer)100, especificacaoParaMaquinas.daMaquina(1).get(1));
	}
	
	@Test
	public void deveriaGerarConfiguracaoIdealPara1MaquinaE2Produtos() throws Exception {
		OfficeBoy officeBoy = new OfficeBoy(especificacoes, listaDePedidos);
		int[] produtos = {1, 2};
		int[] quantidades = {100, 100};
		Pedido pedido = new Pedido(produtos, quantidades);
		listaDePedidos.adicionaPedido(pedido);
		officeBoy.recebePedidoParaTrabalhar();
		EspecificacoesDeMaquinas especificacaoParaMaquinas = officeBoy.geraConfiguracaoIdeal();
		assertFalse(especificacaoParaMaquinas.daMaquina(1).isEmpty());
		assertEquals((Integer)100, especificacaoParaMaquinas.daMaquina(1).get(1));
		
		assertEquals((Integer)100, especificacaoParaMaquinas.daMaquina(1).get(2));
	}
}
