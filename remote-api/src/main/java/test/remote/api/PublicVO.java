package test.remote.api;

import java.io.Serializable;
import java.util.*;

/**
 * @author by Ondřej Buriánek, burianek@marbes.cz.
 * @since 17.3.14
 */
public class PublicVO implements Serializable {

    private Long id;
    private String nazev;
    //private Map<Integer, TypE> mapa;
    private List<String> list;
    private byte[] data;

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

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    /*public Map<Integer, TypE> getMapa() {
        return mapa;
    }

    public void setMapa(Map<Integer, TypE> mapa) {
        this.mapa = mapa;
    }*/

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public enum TypE {
        Prvni, Druhy, Treti
    }

    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    public Date getDate2() {
        return date2;
    }

    public void setDate2(Date date2) {
        this.date2 = date2;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getOpposite() {
        return opposite;
    }

    public void setOpposite(Boolean opposite) {
        this.opposite = opposite;
    }
}
