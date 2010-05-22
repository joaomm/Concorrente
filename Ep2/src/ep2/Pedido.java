package ep2;

import java.util.HashMap;

public class Pedido {

	private static int contagemDePedidos = 1;
	private int id;
	private HashMap<Integer, Integer> produtosEQuantidades;

	public Pedido(int[] produtos, int[] quantidades) {
		id = contagemDePedidos++;
		criaProdutosEQuantidades(produtos, quantidades);
	}

	private void criaProdutosEQuantidades(int[] produtos, int[] quantidades) {
		produtosEQuantidades = new HashMap<Integer, Integer>();
		for (int i = 0; i < produtos.length; i++) 
			produtosEQuantidades.put(produtos[i], quantidades[i]);
	}

	public int id() {
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
		
		if(quantidadeRestante > 0)
			produtosEQuantidades.put(produto, quantidadeRestante);
		else
			produtosEQuantidades.remove(produto);
	}
}
