package project.test;

import org.junit.Before;
import org.junit.Test;
import project.Cocuk;
import project.Gunce;
import project.SoruGunce;

import java.time.LocalDateTime;

import static junit.framework.TestCase.assertTrue;

public class GunceTest {
    private Gunce gunce;
    private SoruGunce soruGunce;

    @Before
    public void setUp(){
        gunce = new Gunce(new Cocuk("ergun","1234"));
        soruGunce = new SoruGunce(2,25,7,8,true);
    }

    @Test
    public void testDate(){
        assertTrue(gunce.getDate().split("T")[0].equals(LocalDateTime.now().toString().split("T")[0]));
    }

    @Test
    public void testCocuk(){
        assertTrue(gunce.getCocuk() instanceof Cocuk);
    }

    @Test
    public void testGunceyeEkle(){
        gunce.addAnsweredSoru(soruGunce);
        assertTrue(gunce.getSorular().size() == 1);
    }
}
