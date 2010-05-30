package test;

import static org.junit.Assert.*;

import org.junit.Test;

import ep2.Pedido;

public class TestaPedido {
	@Test
	public void pedidosNovosNaoDevemTerIdMasStatusDeveSerFalse() throws Exception {
		int[] produtos = {1};
		int[] quantidades = {1000};
		Pedido pedido = new Pedido(produtos, quantidades);
		assertEquals(-1, pedido.id());
		assertFalse(pedido.finalizado());
	}
	
	@Test
	public void deveriaSaberConstruirMapaProdutoQuantidade() throws Exception {
		int[] produtos = {1};
		int[] quantidades = {1000};
		Pedido pedido = new Pedido(produtos, quantidades);
		assertEquals((Integer) 1000, pedido.produtoEQuantidades().get(1));
	}
	
	@Test
	public void deveriaSaberConstruirMapaComDoisProdutos() throws Exception {
		int[] produtos = {1, 2};
		int[] quantidades = {1000, 1000};
		Pedido pedido = new Pedido(produtos, quantidades);
		assertEquals((Integer) 1000, pedido.produtoEQuantidades().get(1));
		assertEquals((Integer) 1000, pedido.produtoEQuantidades().get(2));
	}
	
	@Test
	public void deveriaSaberConstruirMapaComDoisProdutosComQuantidadesDiferentes() throws Exception {
		int[] produtos = {1, 2};
		int[] quantidades = {1000, 2000};
		Pedido pedido = new Pedido(produtos, quantidades);
		assertEquals((Integer) 1000, pedido.produtoEQuantidades().get(1));
		assertEquals((Integer) 2000, pedido.produtoEQuantidades().get(2));
	}
	
	@Test
	public void naoDeveriaDiminuirAQuantidadeSeNaoProduziuNada() throws Exception {
		int[] produtos = {1};
		int[] quantidades = {1000};
		Pedido pedido = new Pedido(produtos, quantidades);
		
		pedido.produzido(1, 0);
		assertEquals((Integer) 1000, pedido.produtoEQuantidades().get(1)); 		
	}
	
	@Test
	public void deveriaDiminuirAQuantidadeProduzida() throws Exception {
		int[] produtos = {1};
		int[] quantidades = {1000};
		Pedido pedido = new Pedido(produtos, quantidades);
		
		pedido.produzido(1, 500);
		assertEquals((Integer) 500, pedido.produtoEQuantidades().get(1)); 		
	}
	
	@Test
	public void deveriaRetirarOProdutoSeProduziuONecessario() throws Exception {
		int[] produtos = {1};
		int[] quantidades = {1000};
		Pedido pedido = new Pedido(produtos, quantidades);
		
		pedido.produzido(1, 1000);
		assertNull(pedido.produtoEQuantidades().get(1)); 		
	}
	
	@Test
	public void deveriaSaberMudarDeStatus() throws Exception {
		int[] produtos = {1};
		int[] quantidades = {1000};
		Pedido pedido = new Pedido(produtos, quantidades);
		
		pedido.produzido(1, 1000);
		assertTrue(pedido.finalizado());
	}

	@Test
	public void naoDeveriaTerTinguemTrabalhandoNeleQuandoEhCriado() throws Exception {
		int[] produtos = {1};
		int[] quantidades = {1000};
		Pedido pedido = new Pedido(produtos, quantidades);
		
		assertFalse(pedido.sendoProduzido());
	}
	
	@Test
	public void deveriaTerAlguemTrabalhandoNeleQuandoPediremParaProduzir() throws Exception {
		int[] produtos = {1};
		int[] quantidades = {1000};
		Pedido pedido = new Pedido(produtos, quantidades);
		pedido.vaiParaProducao();
		assertTrue(pedido.sendoProduzido());
	}
	
	@Test
	public void deveriaReceberUmID() throws Exception {
		int[] produtos = {1};
		int[] quantidades = {1000};
		Pedido pedido = new Pedido(produtos, quantidades);
		assertEquals(-1, pedido.id());
		pedido.id((long)1);
		assertEquals(1, pedido.id());
	}
}
