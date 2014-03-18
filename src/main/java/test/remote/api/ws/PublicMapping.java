package test.remote.api.ws;

import test.remote.api.PublicVO;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author by Ondřej Buriánek, burianek@marbes.cz.
 * @since 17.3.14
 */
public class PublicMapping {

    public static PublicWO copyTo(PublicVO vo, PublicWO wo) {
        wo.setId(vo.getId());
        wo.setNazev(vo.getNazev());

        // umyslne neoptimalizovane
        if (vo.getMapa() != null) {
            wo.setMapa(new ArrayList());
            for (Integer key : vo.getMapa().keySet()) {
                PublicWOMapEntry mapWO = new PublicWOMapEntry();
                mapWO.setKey(key);
                mapWO.setValue(vo.getMapa().get(key).toString());
                wo.getMapa().add(mapWO);
            }
        }
        // umyslne neoptimalizovane
        if (vo.getList() != null) {
            wo.setList(new ArrayList());
            for (String str : vo.getList()) {
                wo.getList().add(str);
            }
        }
        return wo;
    }

    public static PublicVO copyTo(PublicWO wo, PublicVO vo) {
        vo.setId(wo.getId());
        vo.setNazev(wo.getNazev());

        // umyslne neoptimalizovane
        if (vo.getMapa() != null) {
            vo.setMapa(new HashMap<Integer, PublicVO.TypE>());
            for (Object obj : wo.getMapa()) {
                if (obj instanceof PublicWOMapEntry) {
                    PublicWOMapEntry mapWO = (PublicWOMapEntry) obj;
                    vo.getMapa().put(mapWO.getKey(), PublicVO.TypE.valueOf(mapWO.getValue()));
                }
            }
        }
        // umyslne neoptimalizovane
        if (vo.getList() != null) {
            vo.setList(new ArrayList<String>());
            for (Object obj : wo.getList()) {
                if (obj instanceof String)
                    vo.getList().add((String) obj);
            }
        }
        return vo;
    }
}
