package project.test;

import org.junit.Before;
import org.junit.Test;
import project.Alistirma;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class AlistirmaTest {
    private Alistirma alistirma;

    @Before
    public void setUp(){
        alistirma = new Alistirma();
        alistirma.setA(5);
        alistirma.setB(10);
        alistirma.setN(8);
    }

    @Test
    public void testGeneratingSorular(){
        alistirma.generateSorular();
        assertFalse(alistirma.getSorular().isEmpty());
    }

    @Test
    public void testSoruSayisi(){
        alistirma.generateSorular();
        assertTrue(alistirma.getSorular().size() == alistirma.getN());
    }

    @Test
    public void testGap(){
        int gap = alistirma.getB() - alistirma.getA();
        assertFalse(gap < 0);
    }

}
