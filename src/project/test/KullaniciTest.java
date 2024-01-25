package project.test;

import org.junit.Before;
import org.junit.Test;
import project.Cocuk;
import project.Ebeveyn;
import project.Kullanici;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class KullaniciTest {
    private Kullanici kullanici;

    @Before
    public void setUp(){
        kullanici = new Ebeveyn("ahmet","1235");
    }

    @Test
    public void testKullanici(){
        assertTrue(kullanici instanceof Ebeveyn);
        assertFalse(kullanici instanceof Cocuk);
    }

    @Test
    public void testPassword(){
        String newPassword = "ahmet123";
        kullanici.setPassword(newPassword);
        assertTrue(kullanici.getPassword().equals(newPassword));
    }
}
