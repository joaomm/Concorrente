package ep2;

import java.util.HashMap;

public class Pedido {

	private static int contagemDePedidos = 1;
	private int id;
	private HashMap<Integer, Integer> pedidosEQuantidades;

	public Pedido(int[] produtos, int[] quantidades) {
		id = contagemDePedidos++;
		criaProdutosEQuantidades(produtos, quantidades);
	}

	private void criaProdutosEQuantidades(int[] produtos, int[] quantidades) {
		pedidosEQuantidades = new HashMap<Integer, Integer>();
		for (int i = 0; i < produtos.length; i++) 
			pedidosEQuantidades.put(produtos[i], quantidades[i]);
	}

	public int id() {
		return id;
	}

	public boolean finalizado() {
		return pedidosEQuantidades.isEmpty();
	}

	public HashMap<Integer, Integer> produtoEQuantidades() {
		return pedidosEQuantidades;
	}

	public void produzido(int produto, int quantidadeProduzida) {
		int quantidadeInicial = pedidosEQuantidades.get(produto);
		int quantidadeRestante = quantidadeInicial - quantidadeProduzida;
		
		if(quantidadeRestante > 0)
			pedidosEQuantidades.put(produto, quantidadeRestante);
		else
			pedidosEQuantidades.remove(produto);

	}

}
