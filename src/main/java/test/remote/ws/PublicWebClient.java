package test.remote.ws;

import test.remote.api.ws.PublicMapping;
import test.remote.api.PublicVO;
import test.remote.api.ws.PublicWO;
import test.remote.api.ws.PublicWebService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by Ondřej Buriánek, burianek@marbes.cz.
 * @since 17.3.14
 */
public class PublicWebClient {

    PublicWebService publicWebService;

    public void setPublicWebService(PublicWebService publicWebService) {
        this.publicWebService = publicWebService;
    }

    public List<PublicVO> transferPull(boolean complex) {
        try {
            PublicWO[] listWO = publicWebService.transferPullWS(complex);
            List<PublicVO> listVO = new ArrayList<PublicVO>();
            for (PublicWO wo : listWO) {
                listVO.add(PublicMapping.copyTo(wo,new PublicVO()));
            }
            return listVO;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
