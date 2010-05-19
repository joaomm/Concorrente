package ep2;

import java.util.Vector;

public class ListaDePedidos {

	private Vector<Pedido> lista;
	private final ProdutosACriar produtosACriar;

	public ListaDePedidos(ProdutosACriar produtosACriar) {
		this.produtosACriar = produtosACriar;
		lista = new Vector<Pedido>(1000, 100);
	}

	public boolean vazia() {
		return lista.isEmpty();
	}

	public long adicionaPedido(Pedido pedido) {
		int pedidoId = pedido.id();
		produtosACriar.adicionaPedido(pedido);
		lista.setSize(pedidoId + 2);
		lista.set(pedidoId, pedido);
		return pedidoId;
	}

	public Pedido acessaPedido(long id) {
		if (vazia())
			return null;
		return lista.get((int)id);
	}

}
