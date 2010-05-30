package ep2;

import java.util.HashMap;

public class Pedido {

	private long id;
	private HashMap<Integer, Integer> produtosEQuantidades;
	private boolean sendoProduzido;

	public Pedido(int[] produtos, int[] quantidades) {
		id = -1;
		criaProdutosEQuantidades(produtos, quantidades);
		sendoProduzido = false;
	}

	private void criaProdutosEQuantidades(int[] produtos, int[] quantidades) {
		produtosEQuantidades = new HashMap<Integer, Integer>();
		for (int i = 0; i < produtos.length; i++)
			produtosEQuantidades.put(produtos[i], quantidades[i]);
	}

	public void id(Long id) {
		this.id = id;
	}
	
	public long id() {
		return id;
	}

	public boolean finalizado() {
		return produtosEQuantidades.isEmpty();
	}

	public HashMap<Integer, Integer> produtoEQuantidades() {
		return produtosEQuantidades;
	}

	public void produzido(int produto, int quantidadeProduzida) {
		int quantidadeInicial = produtosEQuantidades.get(produto);
		int quantidadeRestante = quantidadeInicial - quantidadeProduzida;

		if (quantidadeRestante > 0)
			produtosEQuantidades.put(produto, quantidadeRestante);
		else
			produtosEQuantidades.remove(produto);
	}

	public boolean sendoProduzido() {
		return sendoProduzido;
	}

	public void vaiParaProducao() {
		sendoProduzido = true;
	}
}
