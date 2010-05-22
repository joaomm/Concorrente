package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ep2.EspecificacoesDeMaquinas;
import ep2.ListaDePedidos;
import ep2.Pedido;
import ep2.Trabalhador;
import ep2.base.Maquina;


public class TestaTrabalhador {
	private Maquina maquina;
	private ListaDePedidos listaDePedidos;
	private EspecificacoesDeMaquinas especificacoes;

	@Before
	public void setUp(){
		especificacoes = new EspecificacoesDeMaquinas();
		especificacoes.adicionaMaquina(1);
		especificacoes.adicionaNaMaquina(1, 1, 100);
		maquina = new Maquina(1, especificacoes.daMaquina(1));
		listaDePedidos = new ListaDePedidos();
	}
	
	@Test
	public void deveriaSerCriadoAssociadoAUmaMaquinaESuasEspecificacoesEConhecerAListaDePedidos() throws Exception {
		new Trabalhador(maquina, especificacoes, listaDePedidos);
	}
	
	@Test
	public void naoDeveriaEscolherNenhumPedidoParaTrabalharSeNaoHaPedidos() throws Exception {
		Trabalhador trabalhador = new Trabalhador(maquina, especificacoes, listaDePedidos);
		assertNull(trabalhador.escolheEmQuePedidoTrabalhar());
	}
	
	
	@Test
	public void deveriaEscolherPrimeiroPedidoParaTrabalhar() throws Exception {
		Trabalhador trabalhador = new Trabalhador(maquina, especificacoes, listaDePedidos);
		int[] produtos = {1};
		int[] quantidades = {100};
		Pedido pedido = new Pedido(produtos, quantidades);
		listaDePedidos.adicionaPedido(pedido);		
		assertEquals(listaDePedidos.acessaPedido(1), trabalhador.escolheEmQuePedidoTrabalhar());
	}
	
	@Test
	public void deveriaEscolherPedido1NoProduto2SePossivel() throws Exception {
		especificacoes = new EspecificacoesDeMaquinas();
		especificacoes.adicionaMaquina(1);
		especificacoes.adicionaNaMaquina(1, 2, 100);
		
		Trabalhador trabalhador = new Trabalhador(maquina, especificacoes, listaDePedidos);

		int[] produtos = {2};
		int[] quantidades = {100};
		Pedido pedido = new Pedido(produtos, quantidades);
		
		listaDePedidos.adicionaPedido(pedido);
		assertNull(trabalhador.escolheEmQuePedidoTrabalhar());
	}
}
