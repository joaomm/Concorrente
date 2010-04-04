EP1 = src/ep1/Ep1.java

TESTE = src/ep1/Teste.java

ALGORITMOS = src/ep1/Algoritmo1.java\
             src/ep1/Algoritmo2.java\
             src/ep1/Algoritmo3.java\
             src/ep1/Algoritmo.java

CALCULADORES = src/ep1/calculadores/CalculadorAlgoritmo1.java\
               src/ep1/calculadores/CalculadorAlgoritmo2.java\
               src/ep1/calculadores/CalculadorAlgoritmo3.java

EDS = src/ep1/eds/Candidato.java\
      src/ep1/eds/Gemeos.java\
      src/ep1/eds/Lista.java\
      src/ep1/eds/MemoriaCompartilhada.java

PRODUTORES = src/ep1/produtores/ProdutorDePrimosGemeos.java\
             src/ep1/produtores/ProdutorDePrimos.java

SRC_FILES = $(EP1) $(TESTE) $(ALGORITMOS) $(CALCULADORES) $(EDS) $(PRODUTORES)


jar: compile
	jar cfm ep1.jar MANIFEST.MF -C bin ep1

compile: $(SRC_FILES)
	mkdir bin
	javac -d bin $(SRC_FILES)

clean:
	rm -r bin ep1.jar