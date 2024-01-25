package project.gui;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;

public class SoruGunceFrame extends JFrame{
    public SoruGunceFrame() throws IOException {
        setTitle("Soru Gunce Frame");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel guncePanel = new JPanel();
        add(guncePanel);

        JLabel lbl = new JLabel("Gunce ismi");
        TextField gunceField = new TextField(5);
        Button gunceBtn = new Button("Gunce Ac",400,200);
        guncePanel.add(lbl);
        guncePanel.add(gunceField);
        guncePanel.add(gunceBtn);

        gunceBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(gunceField.getText()));
                    XSSFSheet sheet = workbook.getSheet("Questions");

                    JTable table = new JTable();
                    table.setPreferredSize(new Dimension(400,600));
                    table.setDefaultEditor(Object.class, null);
                    guncePanel.add(table);

                    DefaultTableModel dftm = new DefaultTableModel();
                    dftm.addColumn("Question Number");
                    dftm.addColumn("a");
                    dftm.addColumn("b");
                    dftm.addColumn("TRUE/FALSE");
                    dftm.addColumn("Time");
                    dftm.addRow(new Object[]{"Question Number", "a", "b", "TRUE/FALSE", "Time"});

                    Row row = null;
                    int i=1;
                    while((row=sheet.getRow(i)) != null){
                        int number =(int) row.getCell(0).getNumericCellValue();
                        int a =(int) row.getCell(1).getNumericCellValue();
                        int b =(int) row.getCell(2).getNumericCellValue();
                        String dogruMu = row.getCell(3).getStringCellValue();
                        int time =(int) row.getCell(4).getNumericCellValue();

                        dftm.addRow(new Object[]{number, a , b, dogruMu, time});
                        i++;
                    }
                    table.setModel(dftm);
                    workbook.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }finally {
                    lbl.setVisible(false);
                    gunceField.setVisible(false);
                    gunceBtn.setVisible(false);
                }
            }
        });
    }
}
