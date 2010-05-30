package ep2;

import java.util.HashMap;

import ep2.base.Maquina;

public class Operario extends Thread {

	private final Maquina maquina;
	private boolean ocupado;
	private HashMap<Integer, Integer> trabalho;
	private Pedido pedido;

	public Operario(Maquina maquina) {
		this.maquina = maquina;
		ocupado = false;
	}

	@Override
	public void run() {
		while (true) {
			if (temTrabalho())
				trabalha();
			else
				skip();
		}
	}

	private boolean temTrabalho() {
		return ocupado;
	}

	private void skip() {
		try {
			sleep(1000);
		} catch (InterruptedException e) {
			System.out.println("Operario mandado embora: " + getId());
			System.exit(0);
		}
	}

	public void trabalha() {
		for (Integer produto : trabalho.keySet()) {
			Integer quantidade = trabalho.get(produto);
			maquina.processa(produto, quantidade);
			pedido.produzido(produto, quantidade);
		}
		ocupado = false;
	}

	public void recebeTrabalho(HashMap<Integer, Integer> trabalho, Pedido pedido) {
		this.trabalho = trabalho;
		this.pedido = pedido;
		ocupado = true;
	}

	public boolean ocupado() {
		return temTrabalho();
	}
}
