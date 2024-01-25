package project.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerLabel extends JLabel {
    private int k=0;

    public TimerLabel() {
        Timer timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                k++;
                int dk = k/60;
                int sn = k%60;
                if(dk==0){
                    setText(String.valueOf(k));
                }else{
                    setText(dk+":"+sn);
                }
            }
        });
        timer.start();
    }

    public void resetTimer(){
        this.k = 0;
    }
    public int getTime(){
        return k;
    }
}
