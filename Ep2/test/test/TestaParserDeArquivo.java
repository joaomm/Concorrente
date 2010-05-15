package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import ep2.EspecificacoesDeMaquinas;
import ep2.ParserDeArquivo;

public class TestaParserDeArquivo {
	private ParserDeArquivo parser;

	@Before
	public void setUp() {
		parser = new ParserDeArquivo("foo");
	}

	@Test
	public void parserDeveriaTerUmMapaVazioSeNaoParceouNada() throws Exception {
		assertTrue(parser.getEspecificacoes().vazias());
	}

	@Test
	public void parseDaMaquina1ComProduto1ETempo1DeveriaAdicionarCorretamenteNoMapa()
			throws Exception {
		parseiEVerificaMapa(1, 1, 1);
	}

	@Test
	public void parseDaMaquina2ComProduto1ETempo1DeveriaAdicionarCorretamenteNoMapa()
			throws Exception {
		parseiEVerificaMapa(2, 1, 1);
	}

	@Test
	public void parseDaMaquina7ComProduto1ETempo1DeveriaAdicionarCorretamenteNoMapa()
			throws Exception {
		parseiEVerificaMapa(7, 1, 1);
	}

	@Test
	public void parseDaMaquina1ComProduto2ETempo1DeveriaAdicionarCorretamenteNoMapa()
			throws Exception {
		parseiEVerificaMapa(1, 2, 1);
	}

	@Test
	public void parseDaMaquina1ComProduto1ETempo2DeveriaAdicionarCorretamenteNoMapa()
			throws Exception {
		parseiEVerificaMapa(1, 1, 2);
	}

	@Test
	public void parseDaMaquina1Com2Produtos() throws Exception {
		parser.parseia("1;1;1");
		parser.parseia("1;2;1");
		EspecificacoesDeMaquinas especificaoes = parser.getEspecificacoes();
		assertEquals((Integer) 1, especificaoes.daMaquina(1).get(1));
		assertEquals((Integer) 1, especificaoes.daMaquina(1).get(2));
	}

	private void parseiEVerificaMapa(int maquinaID, int produto, Integer tempo) {
		parser.parseia("" + maquinaID + ";" + produto + ";" + tempo);
		EspecificacoesDeMaquinas especificacoes = parser.getEspecificacoes();
		assertEquals(tempo, especificacoes.daMaquina(maquinaID).get(produto));
	}
}
