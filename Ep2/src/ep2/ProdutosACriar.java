package ep2;

import java.util.HashMap;
import java.util.Map;

public class ProdutosACriar {
	private Map<Integer, Produto> produtos;

	public ProdutosACriar() {
		produtos = new HashMap<Integer, Produto>();
	}

	public boolean precisaTrabalharEm(int produto) {
		return produtos.get(produto) != null && produtos.get(produto).temQuantidade();
	}

	public void adicionaPedido(Pedido pedido) {
		HashMap<Integer,Integer> produtoEQuantidades = pedido.produtoEQuantidades();
		for (Integer produto : produtoEQuantidades.keySet()) {
			if(produtos.get(produto) == null)
				produtos.put(produto, new Produto(produto));
			produtos.get(produto).adicionaPedido(pedido);
		}
	}

	public int quantidadeASerProduzida(int produto) {
		return produtos.get(produto).quantidadeTotal();
	}

	public void recebeProducao(int produtoId, int quantidade) {
		produtos.get(produtoId).removeQuantidade(quantidade);
	}
}
