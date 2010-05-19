package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ep2.Pedido;
import ep2.Produto;

public class TestaProduto {
	private Produto produto;
	private Pedido pedido1;
	private Pedido pedido2;

	@Before
	public void setUp() {
		int[] produtos1 = { 1 };
		int[] quantidades1 = { 100 };
		int[] produtos2 = { 1, 2 };
		int[] quantidades2 = { 200, 100 };

		produto = new Produto(1);
		pedido1 = new Pedido(produtos1, quantidades1);
		pedido2 = new Pedido(produtos2, quantidades2);
	}

	@Test
	public void deveriaTerQuantidadeTotalQuandoAdicionaSoUmPedido() throws Exception {
		produto.adicionaPedido(pedido1);
		assertEquals(100, produto.quantidadeTotal());
	}

	@Test
	public void deveriaTerQuantidadeTotalParaDoisPedidos() throws Exception {
		produto.adicionaPedido(pedido1);
		produto.adicionaPedido(pedido2);
		assertEquals(300, produto.quantidadeTotal());
	}
	
	@Test
	public void deveriaSaberAssinarContratoRemovendoAQuantidadeDoTotal() throws Exception {
		produto.adicionaPedido(pedido1);
		produto.assinaContrato(50);
		
		assertEquals(50, produto.quantidadeTotal());
	}

	@Test
	public void deveriaRemoverQuantidadeEAtualizarPedido() throws Exception {
		produto.adicionaPedido(pedido1);

		produto.removeQuantidade(50);
		assertEquals((Integer) 50, pedido1.produtoEQuantidades().get(1));
	}

	@Test
	public void deveriaRemoverQuantidadeEAtualizarApenasPrimeiroPedido() throws Exception {
		produto.adicionaPedido(pedido1);
		produto.adicionaPedido(pedido2);

		produto.removeQuantidade(50);
		assertEquals((Integer) 50, pedido1.produtoEQuantidades().get(1));
		assertEquals((Integer) 200, pedido2.produtoEQuantidades().get(1));
	}

	@Test
	public void deveriaRemoverQuantidadeEAtualizarAmbosPedidosQuandoQuantidadeEhMaiorDoQueADoPrimeiro()
			throws Exception {
		produto.adicionaPedido(pedido1);
		produto.adicionaPedido(pedido2);

		produto.removeQuantidade(200);
		assertTrue(pedido1.finalizado());
		assertEquals((Integer) 100, pedido2.produtoEQuantidades().get(1));
	}

	@Test
	public void deveriaSaberDizerSeTemProdutoPraCalcularOuNao() throws Exception {
		assertFalse(produto.temQuantidade());
		produto.adicionaPedido(pedido1);
		assertTrue(produto.temQuantidade());
	}
}
