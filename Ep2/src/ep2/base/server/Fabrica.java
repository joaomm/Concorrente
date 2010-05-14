package ep2.base.server;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import ep2.FabricaImpl;

@WebService
public class Fabrica {

	@WebMethod
	public long produz(@WebParam (name="produtos") int[] produtos, @WebParam(name="quantidades") int[] quantidades) {
		return FabricaImpl.despachaParaProducao(produtos, quantidades);
	}
	
	//devolve false em producao, true pronto
	@WebMethod
	public boolean isProducaoPronta(long id) {
		return FabricaImpl.verificaStatusDaProducao(id);
	}	

}
