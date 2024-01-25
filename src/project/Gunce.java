package project;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Gunce implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private Cocuk cocuk;
    private ArrayList<SoruGunce> sorular;
    private String date;
    private int score;

    public Gunce(Cocuk cocuk) {
        sorular = new ArrayList<SoruGunce>();
        date = LocalDateTime.now().toString();
        this.cocuk = cocuk;
    }

    public void addAnsweredSoru(SoruGunce soruGunce){
        sorular.add(soruGunce);
    }

    public Cocuk getCocuk() {
        return cocuk;
    }

    public ArrayList<SoruGunce> getSorular() {
        return sorular;
    }

    public String getDate() {
        return date;
    }

    public int getScore(){
        return score;
    }
    public void setScore(int score){
        this.score = score;
    }

    @Override
    public String toString() {
        return "Gunce{" +
                "cocuk=" + cocuk +
                ", sorular=" + sorular +
                ", date=" + date +
                ", score=" + score +
                '}';
    }
}
