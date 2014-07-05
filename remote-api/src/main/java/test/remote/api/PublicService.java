package test.remote.api;

import org.apache.cxf.annotations.FastInfoset;

import javax.jws.WebService;
import java.util.List;

/**
 * @author by Ondřej Buriánek, burianek@marbes.cz.
 * @since 17.3.14
 */
@WebService(name = "Public", targetNamespace = "http://remote.test")
@FastInfoset
public interface PublicService {

    List<PublicVO> transferPull(boolean complex) throws PublicException;
}
