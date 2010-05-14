package test;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import ep2.ParserDeArquivo;

public class TestaParserDeArquivo {
	private ParserDeArquivo parser;


	@Before
	public void setUp() {
		parser = new ParserDeArquivo("foo");
	}

	@Test
	public void parserDeveriaTerUmMapaVazioSeNaoParceouNada() throws Exception {
		HashMap<Integer, HashMap<Integer, Integer>> mapaVazio = new HashMap<Integer, HashMap<Integer, Integer>>();
		
		assertEquals(parser.getMapa(), mapaVazio);
	}
	
	@Test
	public void parseDaMaquina1ComProduto1ETempo1DeveriaAdicionarCorretamenteNoMapa() throws Exception {
		parser.parseia("1;1;1");
		HashMap<Integer,HashMap<Integer,Integer>> mapa = parser.getMapa();
		assertEquals(mapa.get(1).get(1), (Integer) 1);
	}
	
	@Test
	public void parseDaMaquina2ComProduto1ETempo1DeveriaAdicionarCorretamenteNoMapa() throws Exception {
		parser.parseia("2;1;1");
		HashMap<Integer,HashMap<Integer,Integer>> mapa = parser.getMapa();
		assertEquals(mapa.get(2).get(1), (Integer) 1);
	}
	
	@Test
	public void parseDaMaquina7ComProduto1ETempo1DeveriaAdicionarCorretamenteNoMapa() throws Exception {
		parser.parseia("7;1;1");
		HashMap<Integer,HashMap<Integer,Integer>> mapa = parser.getMapa();
		assertEquals(mapa.get(7).get(1), (Integer) 1);
	}
	
}
