package project.gui;

import project.Alistirma;
import project.Cocuk;
import project.Ebeveyn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LoginFrame extends JFrame {
    private Ebeveyn ebeveyn;
    private List<Cocuk> cocuklar;
    private Alistirma alistirma;

    public LoginFrame() {
        cocuklar = new ArrayList<Cocuk>();
        txtdenKullanicilariAl();  // Hem tüm kullanıcıları görmek hem de kullanıcı ekleme işleminde kolaylık olduğu için eklenmiştir.
        serilestirmeIleKullanicilariKaydet();
        serilestirilenKullanicilariAl();

        alistirma = new Alistirma();
        serilestirilenAyarlariAl();

        setTitle("Login Frame");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel loginPanel = new JPanel();
        add(loginPanel);
        loginPanel.add(new JLabel("Login Paneli"));
        loginPanel.add(Box.createRigidArea(new Dimension(800,50)));

        JLabel ulbl = new JLabel("Username:");
        JTextField usernameField = new JTextField("",10);
        loginPanel.add(ulbl);
        loginPanel.add(usernameField);

        JLabel plbl = new JLabel("Password:");
        JTextField passwordField = new JTextField("",10);
        loginPanel.add(plbl);
        loginPanel.add(passwordField);

        loginPanel.add(Box.createRigidArea(new Dimension(800,0)));
        Button loginButton = new Button("Login",400,200);
        loginPanel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            int validated = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(usernameField.getText() + "  "+passwordField.getText());

                if(ebeveyn.getUsername().equals(usernameField.getText())  && ebeveyn.getPassword().equals(passwordField.getText())){
                    validated = 1;
                    EbeveynFrame ebeveynFrame = new EbeveynFrame();
                    ebeveynFrame.setEbeveyn(ebeveyn);
                    ebeveynFrame.setAlistirma(alistirma);

                    ebeveynFrame.setVisible(true);
                }
                for(Cocuk c : cocuklar){
                    if(c.getUsername().equals(usernameField.getText()) && c.getPassword().equals(passwordField.getText())){
                        validated = 1;
                        CocukFrame cocukFrame = new CocukFrame();
                        cocukFrame.setCocuk(c);
                        cocukFrame.setAlistirma(alistirma);

                        cocukFrame.setVisible(true);
                    }
                }

                if(validated == 1){
                    serilestirmeIleKullanicilariKaydet();
                    dispose();
                }
            }
        });
    }
    public void setAlistirma(Alistirma alistirma){
        this.alistirma = alistirma;
    }

    private void serilestirmeIleKullanicilariKaydet(){
        try {
            String fileName = "kullanicilar.dat";
            ObjectOutputStream writer = new ObjectOutputStream(
                    new FileOutputStream(fileName));
            writer.writeObject(cocuklar.size());
            writer.writeObject(ebeveyn);
            for(Cocuk c : cocuklar)
                writer.writeObject(c);
            writer.close();
            System.out.println("Kullanicilar serilestirme yontemi kullanilarak kaydedildi");
        }
        catch( IOException ie ) {
            ie.printStackTrace();
        }
    }

    private void serilestirilenAyarlariAl(){
        try {
            ObjectInputStream reader = new ObjectInputStream(
                    new FileInputStream("ayarlar.dat"));
            Integer a = (Integer) reader.readObject();
            Integer b = (Integer) reader.readObject();
            Integer N = (Integer) reader.readObject();

            System.out.println("Serilestirilen ayarlar okundu (a,b,N)-> (" +a + ", "+ b + ", " + N + ")");
            alistirma.setA(a);
            alistirma.setB(b);
            alistirma.setN(N);

            reader.close();
        } catch( IOException ie ) {
            ie.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void serilestirilenKullanicilariAl(){
        try {
            ObjectInputStream reader = new ObjectInputStream(
                    new FileInputStream("kullanicilar.dat"));
            Integer cocukCount = (Integer) reader.readObject();
            ebeveyn = (Ebeveyn) reader.readObject();

            for(int i=0;i<cocukCount;i++){
                cocuklar.add( (Cocuk) reader.readObject());
            }

            System.out.println("Serilestirilen kullanicilar okundu");
            reader.close();
        } catch( IOException ie ) {
            ie.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void txtdenKullanicilariAl() {
        try {
            File file = new File("kullanicilar.txt");
            Scanner s = new Scanner(file);

            while (s.hasNextLine()) {
                String line = s.nextLine();
                String[] items = line.split("\\|");

                String username = items[0];
                String password = items[1];
                String type = items[2];

                if (type.equalsIgnoreCase("ebeveyn")) {
                    ebeveyn = new Ebeveyn(username, password);
                } else if (type.equalsIgnoreCase("cocuk")) {
                    cocuklar.add(new Cocuk(username, password));
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
