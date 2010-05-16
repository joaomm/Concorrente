package ep2;

import java.util.LinkedList;

public class Produto {

	private int quantidadeTotal;
	private final int id;
	private LinkedList<Pedido> pedidos;

	public Produto(int produtoId) {
		id = produtoId;
		pedidos = new LinkedList<Pedido>();
		quantidadeTotal = 0;
	}

	public int quantidadeTotal() {
		return quantidadeTotal;
	}

	public void removeQuantidade(int quantidade) {
		quantidadeTotal -= quantidade;
		Integer quantidadeDoPedidoMaisAntigo = subtraiQuantidadeDoPedidoMaisAntigo(quantidade);

		while (!pedidos.isEmpty() && quantidade >= quantidadeDoPedidoMaisAntigo) {
			pedidos.removeFirst();
			if (!pedidos.isEmpty()) {
				quantidade -= quantidadeDoPedidoMaisAntigo;
				quantidadeDoPedidoMaisAntigo = subtraiQuantidadeDoPedidoMaisAntigo(quantidade);
			}
		}
	}

	private Integer subtraiQuantidadeDoPedidoMaisAntigo(int quantidade) {
		Pedido pedido = pedidos.getFirst();
		Integer quantidadeDoUltimoPedido = pedido.produtoEQuantidades().get(id);
		pedido.produzido(id, quantidade);
		return quantidadeDoUltimoPedido;
	}

	public void adicionaPedido(Pedido pedido) {
		pedidos.addLast(pedido);
		quantidadeTotal += pedido.produtoEQuantidades().get(id);
	}

	public boolean temQuantidade() {
		return !pedidos.isEmpty();
	}

}
