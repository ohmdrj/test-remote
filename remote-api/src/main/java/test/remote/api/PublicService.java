package test.remote.api;

import javax.jws.WebService;
import java.util.List;

/**
 * @author by Ondřej Buriánek, burianek@marbes.cz.
 * @since 17.3.14
 */
@WebService(name = "Public", targetNamespace = "http://remote.test")
public interface PublicService {

    List<PublicVO> transferPull(boolean complex) throws PublicException;
}
