package test.remote.api.ws;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.io.Serializable;
import java.util.*;

/**
 * @author by Ondřej Buriánek, burianek@marbes.cz.
 * @since 17.3.14
 */
@XmlRootElement
@XmlSeeAlso({PublicWOMapEntry.class})
public class PublicWO  implements Serializable {

    private Long id;
    private String nazev;
    //webservices nepodporuji mapy, generics take ne
    private List mapa = new ArrayList();
    private List list = new ArrayList();

    private Date date1 = new Date();
    private Date date2 = new Date();
    private Boolean enabled = true;
    private Boolean opposite = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public List getMapa() {
        return mapa;
    }

    public void setMapa(List mapa) {
        this.mapa = mapa;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

}
