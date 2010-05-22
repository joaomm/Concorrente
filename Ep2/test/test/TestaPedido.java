package test;

import static org.junit.Assert.*;

import org.junit.Test;

import ep2.Pedido;

public class TestaPedido {
	@Test
	public void pedidosNovosDevemTerIdEStatusFalse() throws Exception {
		int[] produtos = {1};
		int[] quantidades = {1000};
		Pedido pedido = new Pedido(produtos, quantidades);
		assertEquals(1, pedido.id());
		assertFalse(pedido.finalizado());
	}
	
	@Test
	public void segundoPedidosDeveTerId2() throws Exception {
		int[] produtos = {1};
		int[] quantidades = {1000};
		Pedido pedido = new Pedido(produtos, quantidades);
		assertEquals(2, pedido.id());
	}
	
	@Test
	public void idDoMesmoPedidoDeveSerSempreIgual() throws Exception {
		int[] produtos = {1};
		int[] quantidades = {1000};
		Pedido pedido = new Pedido(produtos, quantidades);
		assertEquals(pedido.id(), pedido.id());
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
}
