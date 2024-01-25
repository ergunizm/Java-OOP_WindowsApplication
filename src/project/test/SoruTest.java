package project.test;

import org.junit.Before;
import org.junit.Test;
import project.Soru;

import static junit.framework.TestCase.assertTrue;

public class SoruTest {
    private Soru soru;

    @Before
    public void setUp(){
        soru = new Soru(5,6);
    }

    @Test
    public void testResult(){
        assertTrue(soru.getResult() == soru.getA() * soru.getB());
    }
}
