package test.remote;

import com.sun.xml.internal.ws.api.fastinfoset.FastInfosetFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.remoting.jaxws.JaxWsPortProxyFactoryBean;
import test.remote.api.PublicService;

import javax.annotation.PostConstruct;
import java.net.URL;

/**
 * @author by Ondřej Buriánek, burianek@marbes.cz.
 * @since 17.3.14
 */
public class AppControler {

    @Autowired
    PublicService publicInvokerClient;
    @Autowired
    PublicService publicJaxwsClient;
    @Autowired
    PublicWebClient publicWebClient;
    //Manual
    PublicService publicJaxwsBinary;

    @PostConstruct
    public void init() {
        printBeanStatus(publicInvokerClient, "Invoker  client");
        printBeanStatus(publicJaxwsClient, "Jax-WS  client");
        printBeanStatus(publicWebClient, "WebService  client");
    }

    private void printBeanStatus(Object bean, String message) {
        System.out.println(message + ": " + (bean == null ? "NULL" : "OK"));
    }

    public PublicService getPublicInvokerClient() {
        return publicInvokerClient;
    }

    public PublicService getPublicJaxwsClient() {
        return publicJaxwsClient;
    }

    public PublicWebClient getPublicWebClient() {
        return publicWebClient;
    }

    public PublicService getPublicJaxwsBinary() {
        if (publicJaxwsBinary == null) {
            try {
                JaxWsPortProxyFactoryBean factory = new JaxWsPortProxyFactoryBean();
                factory.setServiceInterface(PublicService.class);
                factory.setWsdlDocumentUrl(new URL("http://localhost:8081/Public?WSDL"));
                factory.setNamespaceUri("http://remote.test/");
                factory.setServiceName("Public");
                factory.setPortName("PublicServiceImplPort");
                factory.setLookupServiceOnStartup(true);
                factory.setWebServiceFeatures(new Object[] {
                        new FastInfosetFeature()
                });
                factory.afterPropertiesSet();
                publicJaxwsBinary = (PublicService) factory.getObject();
            } catch (Throwable e) {
                e.printStackTrace();
                //throw new RuntimeException("Error :P", e);
            }
        }
        return publicJaxwsBinary;
    }
}
