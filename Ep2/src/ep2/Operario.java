package ep2;

import java.util.HashMap;

import ep2.base.Maquina;

public class Operario extends Thread {

	private final Maquina maquina;
	private boolean ocupado;
	private HashMap<Integer, Integer> trabalho;

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
			e.printStackTrace();
		}
	}

	public void trabalha() {
		for (Integer produto : trabalho.keySet()) {
			maquina.processa(produto, trabalho.get(produto));
		}
		ocupado = false;
	}

	public void recebeTrabalho(HashMap<Integer, Integer> trabalho) {
		this.trabalho = trabalho;
		ocupado = true;
	}

	public boolean ocupado() {
		return temTrabalho();
	}
}
