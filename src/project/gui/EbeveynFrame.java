package project.gui;

import project.Alistirma;
import project.Ebeveyn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class EbeveynFrame extends JFrame {
    private Ebeveyn ebeveyn;
    private Alistirma alistirma;
    public EbeveynFrame(){
        setTitle("Ebeveyn Frame");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

        JMenu logoutMenu = new JMenu("Log out");
        JMenu raporMenu = new JMenu("Raporlar");
        menuBar.add(logoutMenu);
        menuBar.add(raporMenu);

        JPanel ebeveynPanel = new JPanel();
        add(ebeveynPanel);
        ebeveynPanel.add(new JLabel("Ebeveyn Paneli"));
        ebeveynPanel.add(Box.createRigidArea(new Dimension(800,50)));

        JTextField aField = new JTextField(3);
        JLabel albl = new JLabel("Enter a value");
        ebeveynPanel.add(albl);
        ebeveynPanel.add(aField);
        JTextField bField = new JTextField(3);
        JLabel blbl = new JLabel("Enter b value");
        ebeveynPanel.add(blbl);
        ebeveynPanel.add(bField);
        JTextField nField = new JTextField(3);
        JLabel nlbl = new JLabel("Enter N value");
        ebeveynPanel.add(nlbl);
        ebeveynPanel.add(nField);
        ebeveynPanel.add(Box.createRigidArea(new Dimension(800,0)));

        Button saveButton = new Button("Save",400,200);
        ebeveynPanel.add(saveButton);

        Action logout = new AbstractAction("Log out") {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginFrame frame = new LoginFrame();
                frame.setVisible(true);

                dispose();
            }
        };
        logoutMenu.add(logout);

        raporMenu.add(new AbstractAction("HighScore") {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    HighScoreGunceFrame frame = new HighScoreGunceFrame();
                    frame.setVisible(true);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        raporMenu.add(new AbstractAction("Soru Guncesi") {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    SoruGunceFrame frame = new SoruGunceFrame();
                    frame.setVisible(true);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(aField.getText() + "  "+bField.getText()+ "  "+nField.getText());
                alistirma.setA(Integer.parseInt(aField.getText()));
                alistirma.setB(Integer.parseInt(bField.getText()));
                alistirma.setN(Integer.parseInt(nField.getText()));

                serilestirmeIleAyarlariKaydet();
            }
        });
    }

    public void setEbeveyn(Ebeveyn ebeveyn) {
        this.ebeveyn = ebeveyn;
    }

    public void setAlistirma(Alistirma alistirma) {
        this.alistirma = alistirma;
    }

    private void serilestirmeIleAyarlariKaydet(){
        try {
            String fileName = "ayarlar.dat";
            ObjectOutputStream writer = new ObjectOutputStream(
                    new FileOutputStream(fileName));
            writer.writeObject(alistirma.getA());
            writer.writeObject(alistirma.getB());
            writer.writeObject(alistirma.getN());
            writer.close();
            System.out.println("Ayarlar serilestirme yontemi kullanilarak kaydedildi");
        }
        catch( IOException ie ) {
            ie.printStackTrace();
        }
    }
}
