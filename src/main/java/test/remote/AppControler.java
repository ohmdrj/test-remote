package test.remote;

import org.springframework.beans.factory.annotation.Autowired;
import test.remote.api.PublicFactory;
import test.remote.api.PublicService;
import test.remote.ws.PublicWebClient;
import test.remote.ws.PublicWebServiceEndpoint;

import javax.annotation.PostConstruct;

/**
 * @author by Ondřej Buriánek, burianek@marbes.cz.
 * @since 17.3.14
 */
public class AppControler {

    PublicService publicService;
    PublicService publicClient;
    @Autowired
    PublicWebClient publicWebClient;
    @Autowired
    PublicWebServiceEndpoint publicWebService;
    @Autowired
    PublicFactory publicFactory;

    @PostConstruct
    public void init() {
        printBeanStatus(publicService, "Invoker server");
        printBeanStatus(publicService, "Invoker client");
        printBeanStatus(publicWebService, "Jax-WS  server");
        printBeanStatus(publicWebClient, "Jax-WS  client");
    }

    private void printBeanStatus(Object bean, String message) {
        System.out.println(message + ": " + (bean == null ? "NULL" : "OK"));
    }

    public PublicService getPublicService() {
        return publicService;
    }

    public PublicService getPublicClient() {
        return publicClient;
    }

    public PublicWebClient getPublicWebClient() {
        return publicWebClient;
    }

    public PublicWebServiceEndpoint getPublicWebService() {
        return publicWebService;
    }

    public void setPublicService(PublicService publicService) {
        this.publicService = publicService;
    }

    public void setPublicClient(PublicService publicClient) {
        this.publicClient = publicClient;
    }
}
