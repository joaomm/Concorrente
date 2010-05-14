package ep2.base;

import java.util.Map;

public class Maquina {

	//identificador único da máquina
	private final int id;
	//map com <produto, capacidade de produção/milisegundo> da máquina
	private final Map<Integer, Integer> produtosECapacidades;
	//indica se a máquina está em uso
	private boolean livre = true;
	
	public Maquina (int id, Map<Integer, Integer> produtosECapacidades) {
		this.id = id;
		this.produtosECapacidades = produtosECapacidades;
	}
	
	public void processa (int produto, int quantidade) {
		synchronized (this) {
			if (livre)
				livre = false;
			else
				throw new RuntimeException ("Máquina ocupada. Acessos concorrentes?");
		}
		try {	
			Integer capacidade = produtosECapacidades.get(produto);
			if (capacidade == null)
				throw new RuntimeException ("Máquina não é capaz de produzir este produto");
			else {
				int tempo = (int) Math.ceil((float)quantidade / capacidade); 
				Thread.sleep(tempo);
			}
		} catch (InterruptedException e) {
			throw new RuntimeException ("A produção da máquina" + id + "foi interrompida", e);
		} finally {
			synchronized (this) {
				livre = true;
			}
		}
	}
	
	public boolean isLivre() {
		return livre;
	}
	
}
