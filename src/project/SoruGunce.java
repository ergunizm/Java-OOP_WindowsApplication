package project;

public class SoruGunce implements java.io.Serializable {
    private static final long serialVersionUID = 2L;
    private int soruNumber; //hangisoru
    private int sure;   //ne kadar zamanda tamamladı
    private int a;  // a*b deki a
    private int b;  // a*b deki b
    private boolean dogruMu;   //dogruysa 1, yanlissa 0

    public SoruGunce(int soruNumber, int sure, int a, int b, boolean dogruMu) {
        this.soruNumber = soruNumber;
        this.sure = sure;
        this.a = a;
        this.b = b;
        this.dogruMu = dogruMu;
    }

    public int getSoruNumber() {
        return soruNumber;
    }

    public int getSure() {
        return sure;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public boolean isDogruMu() {
        return dogruMu;
    }

    @Override
    public String toString() {
        return "SoruGunce{" +
                "soruNumber=" + soruNumber +
                ", sure=" + sure +
                ", a=" + a +
                ", b=" + b +
                ", dogruMu=" + dogruMu +
                '}';
    }
}
