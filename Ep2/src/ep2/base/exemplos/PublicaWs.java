package ep2.base.exemplos;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.xml.ws.Endpoint;

import ep2.base.server.Fabrica;

public class PublicaWs {

	public static void main(String[] args) throws IOException {
		ExecutorService threads = Executors.newCachedThreadPool();
		Fabrica service = new Fabrica();
		final Endpoint endpoint = Endpoint.create(service);
		endpoint.setExecutor(threads);
		Thread th = new Thread() {
			public void run() {
				endpoint.publish("http://localhost:8080/fabrica");
			}
		};
		th.start();
		System.out.println("Digite algo seguido de enter para finalizar");
		System.in.read();
		endpoint.stop();
		threads.shutdown();
	}

}
