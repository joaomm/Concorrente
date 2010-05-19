package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import ep2.ListaDePedidos;
import ep2.Pedido;
import ep2.ProdutosACriar;

public class TestaListaDePedidos {
	private Pedido pedido1;
	private Pedido pedido2;
	private ListaDePedidos listaDePedidos;
	private int[] produtos1 = { 1 };
	private int[] quantidades1 = { 100 };
	private ProdutosACriar produtosACriar;

	@Before
	public void setUp() {
		int[] produtos2 = { 1, 2 };
		int[] quantidades2 = { 200, 100 };

		pedido1 = new Pedido(produtos1, quantidades1);
		pedido2 = new Pedido(produtos2, quantidades2);

		produtosACriar = new ProdutosACriar();
		listaDePedidos = new ListaDePedidos(produtosACriar);		
	}

	@Test
	public void deveriaCriarAListaVazia() throws Exception {
		assertTrue(listaDePedidos.vazia());
	}

	@Test
	public void deveriaAdicionarUmPedidoFazendoComQueAListaNaoFiqueVazia() throws Exception {
		listaDePedidos.adicionaPedido(pedido1);
		assertFalse(listaDePedidos.vazia());
	}

	@Test
	public void deveriaAdicionarUmPedidoRetornandoOId() throws Exception {
		assertEquals(pedido1.id(), listaDePedidos.adicionaPedido(pedido1));
	}

	@Test
	public void acessarPedidoDeveriaSerNullSeListaVazia() throws Exception {
		assertNull(listaDePedidos.acessaPedido(1));
	}

	@Test
	public void acessarPedido1DeveriaRetornarOPedido1() throws Exception {
		listaDePedidos.adicionaPedido(pedido1);
		assertEquals(pedido1, listaDePedidos.acessaPedido(pedido1.id()));
	}

	@Test
	public void acessarPedido2DeveriaRetornarOPedido2() throws Exception {
		listaDePedidos.adicionaPedido(pedido2);
		assertEquals(pedido2, listaDePedidos.acessaPedido(pedido2.id()));
	}

	@Test
	public void acessarAmbosPedidosDeveriaRetornarOsRespectivosPedidos() throws Exception {
		listaDePedidos.adicionaPedido(pedido2);
		listaDePedidos.adicionaPedido(pedido1);
		assertEquals(pedido2, listaDePedidos.acessaPedido(pedido2.id()));
		assertEquals(pedido1, listaDePedidos.acessaPedido(pedido1.id()));
	}
	
	@Test
	public void adicionarPedidoComUmProdutoAdicionaEleNaListaDeProdutosACriar() throws Exception {
		assertFalse(produtosACriar.precisaTrabalharEm(1));
		listaDePedidos.adicionaPedido(pedido1);
		assertTrue(produtosACriar.precisaTrabalharEm(1));
	}

	@Test
	public void stressTest() throws Exception {
		for (int i = 0; i < 100000; i++)
			listaDePedidos.adicionaPedido(new Pedido(produtos1, quantidades1));
	}
}
