package test.remote.api.ws;

import javax.jws.WebService;

/**
 * @author by Ondřej Buriánek, burianek@marbes.cz.
 * @since 17.3.14
 */
@WebService(name = "PublicWS", targetNamespace = "http://ws.remote.test")
public interface PublicWebService {

    PublicWO[] transferPullWS(boolean complex) ;
}
