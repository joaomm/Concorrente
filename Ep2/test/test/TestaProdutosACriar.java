package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ep2.Pedido;
import ep2.ProdutosACriar;

public class TestaProdutosACriar {
	private ProdutosACriar produtosACriar;

	@Before
	public void setUp() {
		produtosACriar = new ProdutosACriar();
	}

	@Test
	public void noInicioNaoDeveriaTerProdutoASerCriado() throws Exception {
		ProdutosACriar produtosACriar = new ProdutosACriar();
		assertFalse(produtosACriar.precisaTrabalharEm(1));
	}

	@Test
	public void deveriaTerProdutoASerCriadoQuantoColocaPedidoNovo() throws Exception {
		int[] produtos = { 1 };
		int[] quantidades = { 100 };
		produtosACriar.adicionaPedido(new Pedido(produtos, quantidades));
		assertTrue(produtosACriar.precisaTrabalharEm(1));
	}

	@Test
	public void deveriaDizerQuantoPrecisaTrabalharNumProdutoComQuantidade100() throws Exception {
		ProdutosACriar produtosACriar = new ProdutosACriar();

		int[] produtos = { 1 };
		int[] quantidades = { 100 };
		Pedido pedido = new Pedido(produtos, quantidades);

		produtosACriar.adicionaPedido(pedido);
		assertEquals(100, produtosACriar.quantidadeASerProduzida(1));
	}

	@Test
	public void deveriaDizerQuantoPrecisaTrabalharNumProdutoComQuantidade10() throws Exception {
		ProdutosACriar produtosACriar = new ProdutosACriar();

		int[] produtos = { 1 };
		int[] quantidades = { 10 };
		Pedido pedido = new Pedido(produtos, quantidades);

		produtosACriar.adicionaPedido(pedido);
		assertEquals(10, produtosACriar.quantidadeASerProduzida(1));
	}

	@Test
	public void deveriaDizerQuantoPrecisaTrabalharNumProdutoDiferenteDe1ComQuantidade100()
			throws Exception {
		ProdutosACriar produtosACriar = new ProdutosACriar();

		int[] produtos = { 2 };
		int[] quantidades = { 100 };
		produtosACriar.adicionaPedido(new Pedido(produtos, quantidades));
		assertEquals(100, produtosACriar.quantidadeASerProduzida(2));
	}

	@Test
	public void deveriaReceberDoisPedidosComMesmoProdutoEAQuantidadeaSerProduzidaDeveriaAumentar()
			throws Exception {
		ProdutosACriar produtosACriar = new ProdutosACriar();

		int[] produtos = { 2 };
		int[] quantidades = { 100 };
		produtosACriar.adicionaPedido(new Pedido(produtos, quantidades));
		produtosACriar.adicionaPedido(new Pedido(produtos, quantidades));
		assertEquals(200, produtosACriar.quantidadeASerProduzida(2));
	}

	@Test
	public void deveriaRemoverQuantidadeProduzida() throws Exception {
		ProdutosACriar produtosACriar = new ProdutosACriar();

		int[] produtos = { 2 };
		int[] quantidades = { 100 };
		produtosACriar.adicionaPedido(new Pedido(produtos, quantidades));
		produtosACriar.recebeProducao(2, 50);
		assertEquals(50, produtosACriar.quantidadeASerProduzida(2));
	}

	@Test
	public void deveriaRemoverOutraQuantidadeProduzida() throws Exception {
		ProdutosACriar produtosACriar = new ProdutosACriar();

		int[] produtos = { 2 };
		int[] quantidades = { 1000 };
		produtosACriar.adicionaPedido(new Pedido(produtos, quantidades));
		produtosACriar.recebeProducao(2, 50);
		assertEquals(950, produtosACriar.quantidadeASerProduzida(2));
	}

	@Test
	public void deveriaRemoverQuantidadeProduzidaDeOutroProduto() throws Exception {
		ProdutosACriar produtosACriar = new ProdutosACriar();

		int[] produtos = { 1 };
		int[] quantidades = { 1000 };
		produtosACriar.adicionaPedido(new Pedido(produtos, quantidades));
		produtosACriar.recebeProducao(1, 50);
		assertEquals(950, produtosACriar.quantidadeASerProduzida(1));
	}

	@Test
	public void naoDeveriaPrecisarTrabalharNumProdutoDeposiDeEsgotarTalNecessidade() throws Exception {
		ProdutosACriar produtosACriar = new ProdutosACriar();

		int[] produtos = { 1 };
		int[] quantidades = { 100 };
		produtosACriar.adicionaPedido(new Pedido(produtos, quantidades));
		produtosACriar.recebeProducao(1, 100);
		assertFalse(produtosACriar.precisaTrabalharEm(1));
	}
	
	@Test
	public void pedidoDeveriaAlterarStatus() throws Exception {
		ProdutosACriar produtosACriar = new ProdutosACriar();

		int[] produtos = { 1 };
		int[] quantidades = { 100 };
		Pedido pedido = new Pedido(produtos, quantidades);
		produtosACriar.adicionaPedido(pedido);
		produtosACriar.recebeProducao(1, 100);
		assertTrue(pedido.finalizado());
	}
}
