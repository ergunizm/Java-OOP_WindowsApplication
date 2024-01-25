package project;

import java.util.ArrayList;
import java.util.Random;

public class Alistirma implements java.io.Serializable{
    private static final long serialVersionUID = 1L;
    private int a;
    private int b;
    private int N;
    private ArrayList<Soru> sorular;

    public Alistirma() {
    }

    public void generateSorular(){
        sorular = new ArrayList<>();
        Random random = new Random();
        for(int i=0;i<N;i++){
            Soru tmp = new Soru(random.nextInt(b+1-a)+a, random.nextInt(b+1-a)+a);
            sorular.add(tmp);
        }
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getN() {
        return N;
    }

    public void setN(int n) {
        N = n;
    }

    public ArrayList<Soru> getSorular() {
        return sorular;
    }


    @Override
    public String toString() {
        return "Alistirma{" +
                "a=" + a +
                ", b=" + b +
                ", N=" + N +
                '}';
    }
}
