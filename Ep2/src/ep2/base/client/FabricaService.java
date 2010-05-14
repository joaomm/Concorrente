
package ep2.base.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebServiceClient(name = "FabricaService", targetNamespace = "http://server.base.ep2/", wsdlLocation = "http://localhost:8080/fabrica?wsdl")
public class FabricaService
    extends Service
{

    private final static URL FABRICASERVICE_WSDL_LOCATION;
    private final static Logger logger = Logger.getLogger(ep2.base.client.FabricaService.class.getName());

    static {
        URL url = null;
        try {
            URL baseUrl;
            baseUrl = ep2.base.client.FabricaService.class.getResource(".");
            url = new URL(baseUrl, "http://localhost:8080/fabrica?wsdl");
        } catch (MalformedURLException e) {
            logger.warning("Failed to create URL for the wsdl Location: 'http://localhost:8080/fabrica?wsdl', retrying as a local file");
            logger.warning(e.getMessage());
        }
        FABRICASERVICE_WSDL_LOCATION = url;
    }

    public FabricaService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public FabricaService() {
        super(FABRICASERVICE_WSDL_LOCATION, new QName("http://server.base.ep2/", "FabricaService"));
    }

    /**
     * 
     * @return
     *     returns Fabrica
     */
    @WebEndpoint(name = "FabricaPort")
    public Fabrica getFabricaPort() {
        return super.getPort(new QName("http://server.base.ep2/", "FabricaPort"), Fabrica.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns Fabrica
     */
    @WebEndpoint(name = "FabricaPort")
    public Fabrica getFabricaPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://server.base.ep2/", "FabricaPort"), Fabrica.class, features);
    }

}
