package ep2;

public class FabricaImpl {

	public static boolean verificaStatusDaProducao(long id) {
		System.out.println("Chamou Verifica Status!!!");
		return Servidor.getStatusDoPedido(id);
	}

	public static long despachaParaProducao(int[] produtos, int[] quantidades) {
		System.out.println("Chamou despacha para producao");
		return Servidor.criaPedido(produtos, quantidades);
	}
}
