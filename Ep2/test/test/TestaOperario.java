package test;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import resources.MaquinaMock;
import ep2.Operario;

public class TestaOperario {

	private Operario operario;
	private MaquinaMock maquina;
	private HashMap<Integer, Integer> trabalho;

	@Before
	public void setUp() throws Exception {
		HashMap<Integer, Integer> produtosECapacidades = new HashMap<Integer, Integer>();
		maquina = new MaquinaMock(1, produtosECapacidades);
		operario = new Operario(maquina);

		trabalho = new HashMap<Integer, Integer>();
	}

	@Test
	public void deveriaEstarDesocupadoQuandoCriado() throws Exception {
		assertFalse(operario.ocupado());
	}

	@Test
	public void deveriaEstarOcupadoQuandoRecebeTrabalho() throws Exception {
		operario.recebeTrabalho(trabalho);
		assertTrue(operario.ocupado());
	}

	@Test
	public void deveriaTrabalharNoProduto1() throws Exception {
		trabalho.put(1, 100);
		operario.recebeTrabalho(trabalho);
		operario.trabalha();
		assertTrue(maquina.processou(1, 100));
	}

	@Test
	public void deveriaTrabalharNoProduto2() throws Exception {
		trabalho.put(2, 100);
		operario.recebeTrabalho(trabalho);
		operario.trabalha();
		assertTrue(maquina.processou(2, 100));
	}

	@Test
	public void deveriaTrabalharNoProduto1e2ComQuantidadesDiferentes() throws Exception {
		trabalho.put(1, 200);
		trabalho.put(2, 100);
		operario.recebeTrabalho(trabalho);
		operario.trabalha();
		assertTrue(maquina.processou(1, 200));
		assertTrue(maquina.processou(2, 100));
	}
	
	@Test
	public void deveriaEstarDesocupadoAposTrabalhar() throws Exception {
		trabalho.put(2, 100);
		operario.recebeTrabalho(trabalho);
		operario.trabalha();
		assertFalse(operario.ocupado());
	}
}
