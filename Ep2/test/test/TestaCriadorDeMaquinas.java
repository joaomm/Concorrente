package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import ep2.CriadorDeMaquinas;
import ep2.EspecificacoesDeMaquinas;
import ep2.base.Maquina;

public class TestaCriadorDeMaquinas {
	private EspecificacoesDeMaquinas especificacoes;
	private CriadorDeMaquinas criadorDeMaquinas;

	@Before
	public void setUp() {
		especificacoes = new EspecificacoesDeMaquinas();
		criadorDeMaquinas = new CriadorDeMaquinas(especificacoes);
	}

	@Test
	public void naoDeveriaCriarMaquinasDadasEspecificacoesVazias() throws Exception {
		HashMap<Integer, Maquina> maquinas = criadorDeMaquinas.crie();
		HashMap<Integer, Maquina> mapaVazio = new HashMap<Integer, Maquina>();
		assertEquals(mapaVazio, maquinas);
	}

	@Test
	public void deveriaCriarMaquina1DadoMapaComUmElemento() throws Exception {
		especificacoes.adicionaMaquina(1);
		especificacoes.adicionaNaMaquina(1, 1, 1);

		HashMap<Integer, Maquina> maquinas = criadorDeMaquinas.crie();
		assertNotNull(maquinas.get(1));
		assertTrue(maquinas.get(1) instanceof Maquina);
	}

	@Test
	public void deveriaCriarDuasMaquinasDadoMapaComDoisElementos() throws Exception {
		especificacoes.adicionaMaquina(1);
		especificacoes.adicionaNaMaquina(1, 1, 1);
		especificacoes.adicionaMaquina(2);
		especificacoes.adicionaNaMaquina(2, 1, 1);

		HashMap<Integer, Maquina> maquinas = criadorDeMaquinas.crie();
		assertEquals(2, maquinas.size());
	}
}