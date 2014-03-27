package test.remote;

import org.springframework.beans.factory.annotation.Autowired;
import test.remote.api.PublicService;

/**
 * @author by Ondřej Buriánek, burianek@marbes.cz.
 * @since 27.3.14
 */
public class AppServer {

    @Autowired
    PublicService publicService;

}
