EP2 = src/ep2/Servidor.java



EP2_NOSSO = src/ep2/CasaDeMaquinas.java\
	src/ep2/EspecificacoesDeMaquinas.java\
	src/ep2/FabricaImpl.java\
	src/ep2/LeitorDeArquivo.java\
	src/ep2/ListaDePedidos.java\
	src/ep2/OfficeBoy.java\
	src/ep2/Operario.java\
	src/ep2/ParserDeArquivo.java\
	src/ep2/Pedido.java\
	src/ep2/Servidor.java

MAQUINA = src/ep2/base/Maquina.java

CLIENTE = src/ep2/base/client/Fabrica.java\
	src/ep2/base/client/FabricaService.java\
	src/ep2/base/client/IsProducaoPronta.java\
	src/ep2/base/client/IsProducaoProntaResponse.java\
	src/ep2/base/client/ObjectFactory.java\
	src/ep2/base/client/package-info.java\
	src/ep2/base/client/Produz.java\
	src/ep2/base/client/ProduzResponse.java

SERVER = src/ep2/base/server/Fabrica.java



SRC_FILES = $(EP2_NOSSO) $(MAQUINA) $(CLIENTE) $(SERVER)


jar: compile
	jar cfm ep2.jar MANIFEST.MF -C bin ep2

compile: $(SRC_FILES)
	mkdir -p bin
	javac -d bin $(SRC_FILES)

clean:
	rm -r bin ep2.jar