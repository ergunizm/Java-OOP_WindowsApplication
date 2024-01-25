package project;

import project.gui.LoginFrame;

import java.awt.*;

public class Main {
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                LoginFrame frame = new LoginFrame();
                frame.setVisible(true);
            }
        });
    }
}
