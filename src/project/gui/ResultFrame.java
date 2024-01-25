package project.gui;

import project.Gunce;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ResultFrame extends JFrame {
    private Gunce gunce;

    public ResultFrame(Gunce gunce){
        this.gunce = gunce;

        setTitle(gunce.getCocuk().getUsername()+" Report Frame");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel resultPanel = new JPanel();
        add(resultPanel);

        JTable table = new JTable();
        table.setPreferredSize(new Dimension(400,600));
        table.setDefaultEditor(Object.class, null);
        resultPanel.add(table);

        DefaultTableModel dftm = new DefaultTableModel();
        dftm.addColumn("Question Number");
        dftm.addColumn("a");
        dftm.addColumn("b");
        dftm.addColumn("TRUE/FALSE");
        dftm.addColumn("Time");
        dftm.addRow(new Object[]{"Username", "Score", "Start Time"});
        dftm.addRow(new Object[]{gunce.getCocuk().getUsername(), gunce.getScore(), gunce.getDate()});
        dftm.addRow(new Object[]{}); //okunabilirlik açısından 1 satır boşluk bıraktım

        dftm.addRow(new Object[]{"Question Number", "a", "b", "TRUE/FALSE", "Time"});

        resultPanel.add(Box.createRigidArea(new Dimension(800,0)));

        for(int i = 0; i < gunce.getSorular().size(); i++) {
            dftm.addRow(new Object[]{gunce.getSorular().get(i).getSoruNumber(), gunce.getSorular().get(i).getA() , gunce.getSorular().get(i).getB(), gunce.getSorular().get(i).isDogruMu(), gunce.getSorular().get(i).getSure()});
        }
        table.setModel(dftm);

    }
}
