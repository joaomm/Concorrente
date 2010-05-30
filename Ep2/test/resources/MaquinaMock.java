package resources;

import java.util.LinkedList;
import java.util.Map;

import ep2.base.Maquina;

public class MaquinaMock extends Maquina{

	class Dupla{
		public final int produto;
		public final int quantidade;

		public Dupla(int produto, int quantidade) {
			this.produto = produto;
			this.quantidade = quantidade;
		}
		
		@Override
		public boolean equals(Object obj) {
			Dupla outro = (Dupla) obj;
			return outro.produto == produto && outro.quantidade == quantidade;
		}
	}

	private LinkedList<Dupla> processados;

	public MaquinaMock(int id, Map<Integer, Integer> produtosECapacidades) {
		super(id, produtosECapacidades);
		processados = new LinkedList<Dupla>();
	}

	@Override
	public void processa(int produto, int quantidade) {
		processados.add(new Dupla(produto, quantidade));
	}
	
	public boolean processou(int produto, int quantidade) {
		return processados.contains(new Dupla(produto, quantidade));
	}
	
}
