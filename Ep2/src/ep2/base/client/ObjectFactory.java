
package ep2.base.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ep2.base.client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Produz_QNAME = new QName("http://server.base.ep2/", "produz");
    private final static QName _IsProducaoProntaResponse_QNAME = new QName("http://server.base.ep2/", "isProducaoProntaResponse");
    private final static QName _IsProducaoPronta_QNAME = new QName("http://server.base.ep2/", "isProducaoPronta");
    private final static QName _ProduzResponse_QNAME = new QName("http://server.base.ep2/", "produzResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ep2.base.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Produz }
     * 
     */
    public Produz createProduz() {
        return new Produz();
    }

    /**
     * Create an instance of {@link IsProducaoPronta }
     * 
     */
    public IsProducaoPronta createIsProducaoPronta() {
        return new IsProducaoPronta();
    }

    /**
     * Create an instance of {@link ProduzResponse }
     * 
     */
    public ProduzResponse createProduzResponse() {
        return new ProduzResponse();
    }

    /**
     * Create an instance of {@link IsProducaoProntaResponse }
     * 
     */
    public IsProducaoProntaResponse createIsProducaoProntaResponse() {
        return new IsProducaoProntaResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Produz }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.base.ep2/", name = "produz")
    public JAXBElement<Produz> createProduz(Produz value) {
        return new JAXBElement<Produz>(_Produz_QNAME, Produz.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IsProducaoProntaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.base.ep2/", name = "isProducaoProntaResponse")
    public JAXBElement<IsProducaoProntaResponse> createIsProducaoProntaResponse(IsProducaoProntaResponse value) {
        return new JAXBElement<IsProducaoProntaResponse>(_IsProducaoProntaResponse_QNAME, IsProducaoProntaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IsProducaoPronta }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.base.ep2/", name = "isProducaoPronta")
    public JAXBElement<IsProducaoPronta> createIsProducaoPronta(IsProducaoPronta value) {
        return new JAXBElement<IsProducaoPronta>(_IsProducaoPronta_QNAME, IsProducaoPronta.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProduzResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.base.ep2/", name = "produzResponse")
    public JAXBElement<ProduzResponse> createProduzResponse(ProduzResponse value) {
        return new JAXBElement<ProduzResponse>(_ProduzResponse_QNAME, ProduzResponse.class, null, value);
    }

}
