package ep2;

import java.util.HashMap;

public class ListaDePedidos {

	private HashMap<Long, Pedido> pedidos;
	private long idAtual;
	private long ultimoPedido;

	public ListaDePedidos() {
		pedidos = new HashMap<Long, Pedido>(1000, 100);
		idAtual = 1;
		ultimoPedido = 1;
	}

	public synchronized boolean vazia() {
		return pedidos.isEmpty();
	}

	public synchronized long adicionaPedido(Pedido pedido) {
		long pedidoId = idAtual++;
		pedido.id(pedidoId);
		pedidos.put(pedidoId, pedido);
		return pedidoId;
	}

	public synchronized Pedido acessaPedido(long id) {
		if (vazia())
			return null;
		return pedidos.get(id);
	}

	public synchronized Pedido proximoPedido() {
		if (pedidos.get(ultimoPedido) == null)
			return null;

		if (pedidos.get(ultimoPedido).sendoProduzido())
			ultimoPedido++;

		return pedidos.get(ultimoPedido);
	}
}
