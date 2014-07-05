package test.remote.api;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author by Ondřej Buriánek, burianek@marbes.cz.
 * @since 17.3.14
 */
public class PublicFactory {

    byte[] imageData;
    Random random = new Random();
    List<PublicVO> exampleSimple = new ArrayList<PublicVO>();
    List<PublicVO> exampleComplex = new ArrayList<PublicVO>();

    public PublicFactory() {
        for (int i = 1; i < 100; i++) {
            exampleSimple.add(newPublicVO((long) i, "Datovy Test " + i, 10, 10));
        }
        try {
            imageData = IOUtils.toByteArray(getClass().getResourceAsStream("/freeski.jpg"));
            exampleComplex.add(newPublicVO(0l, "Prvni Obrazek", imageData));
            for (int i = 1; i < 100; i++) {
                exampleSimple.add(newPublicVO((long) i, "Datovy Test " + i, 10, 100));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<PublicVO> getExampleSimple() {
        return exampleSimple;
    }

    public List<PublicVO> getExampleComplex() {
        return exampleComplex;
    }

    public PublicVO newPublicVO(Long id, String name, byte[] data) {
        PublicVO vo = new PublicVO();
        vo.setId(id);
        vo.setNazev(name);
        vo.setData(data);
        return vo;
    }

    public PublicVO newPublicVO(Long id, String name, int testMap, int testList) {
        PublicVO vo = new PublicVO();
        vo.setId(id);
        vo.setNazev(name);
//        vo.setMapa(new HashMap<Integer, PublicVO.TypE>());
        vo.setList(new ArrayList<String>());
//        for (int i = 0; i < testMap; i++) {
//            vo.getMapa().put(i, randomTypE());
//        }
        for (int i = 0; i < testList; i++) {
            vo.getList().add(randomString());
        }
        return vo;
    }

    private PublicVO.TypE randomTypE() {
        return PublicVO.TypE.values()[random.nextInt(3)];
    }

    private String randomString() {
        int length = random.nextInt(80);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char c = (char) (random.nextInt((int) (Character.MAX_VALUE)));
            sb.append(c);
        }
        return Base64.encodeBase64String(sb.toString().getBytes());
    }
}
