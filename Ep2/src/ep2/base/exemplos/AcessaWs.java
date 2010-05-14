package ep2.base.exemplos;

import java.util.Arrays;

import ep2.base.client.Fabrica;
import ep2.base.client.FabricaService;

public class AcessaWs {

	public static void main(String[] args) {
		Fabrica port = new FabricaService().getFabricaPort();
		Integer[] produtos = new Integer[] {1, 2, 3, 4};
		Integer[] quantidades = new Integer[] {5000, 10000, 20000, 30000};
		System.out.println(port.produz(Arrays.asList(produtos), Arrays.asList(quantidades)));
		System.out.println(port.isProducaoPronta(1));
	}

}
