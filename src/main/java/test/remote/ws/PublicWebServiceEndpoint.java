package test.remote.ws;

import org.springframework.beans.factory.annotation.Autowired;
import test.remote.api.*;
import test.remote.api.ws.PublicMapping;
import test.remote.api.ws.PublicWO;
import test.remote.api.ws.PublicWebService;

import javax.jws.WebService;
import java.util.ArrayList;

/**
 * @author by Ondřej Buriánek, burianek@marbes.cz.
 * @since 17.3.14
 */
@WebService(serviceName = "PublicWS", endpointInterface = "test.remote.api.ws.PublicWebService")
public class PublicWebServiceEndpoint implements PublicWebService {

    @Autowired
    PublicService publicService;

    public PublicWO[] transferPullWS(boolean complex) {
        try {
            ArrayList<PublicWO> list = new ArrayList<PublicWO>();
            for (PublicVO vo : publicService.transferPull(complex)) {
                list.add(PublicMapping.copyTo(vo, new PublicWO()));
            }
            return list.toArray(new PublicWO[list.size()]);
        } catch (PublicException e) {
            e.printStackTrace();
            return null;
        }
    }

}
