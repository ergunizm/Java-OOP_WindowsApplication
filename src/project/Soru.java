package project;

public class Soru implements java.io.Serializable{
    private static final long serialVersionUID = 2L;
    private int a;
    private int b;
    private int result;

    public Soru(int a, int b) {
        this.a = a;
        this.b = b;
        this.result = a*b;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public int getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "Soru{" +
                "a=" + a +
                ", b=" + b +
                ", result=" + result +
                '}';
    }
}
