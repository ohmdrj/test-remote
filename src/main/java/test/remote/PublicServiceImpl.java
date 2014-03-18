package test.remote;

import org.springframework.beans.factory.annotation.Autowired;
import test.remote.api.PublicException;
import test.remote.api.PublicFactory;
import test.remote.api.PublicService;
import test.remote.api.PublicVO;

import java.util.List;

/**
 * @author by Ondřej Buriánek, burianek@marbes.cz.
 * @since 17.3.14
 */
public class PublicServiceImpl implements PublicService {

    @Autowired
    PublicFactory publicFactory;

    @Override
    public List<PublicVO> transferPull(boolean complex) throws PublicException {
        return complex ? publicFactory.getExampleComplex() : publicFactory.getExampleSimple();
    }
}
