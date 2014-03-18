package test.remote.api;

import java.util.List;

/**
 * @author by Ondřej Buriánek, burianek@marbes.cz.
 * @since 17.3.14
 */
public interface PublicService {

    List<PublicVO> transferPull(boolean complex) throws PublicException;
}
