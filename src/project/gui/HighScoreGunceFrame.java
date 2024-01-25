package project.gui;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;

public class HighScoreGunceFrame extends JFrame{

    public HighScoreGunceFrame() throws IOException {
        setTitle("HighScore Frame");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel guncePanel = new JPanel();
        add(guncePanel);
        JTable table = new JTable();
        table.setPreferredSize(new Dimension(400,600));
        table.setDefaultEditor(Object.class, null);
        guncePanel.add(table);

        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream("Highscore.xlsx"));
        XSSFSheet sheet = workbook.getSheet("Highscore");

        DefaultTableModel dftm = new DefaultTableModel();
        dftm.addColumn("Username");
        dftm.addColumn("Date");
        dftm.addColumn("Score");
        dftm.addRow(new Object[]{"Username", "Date", "Score"});

        Row row = null;
        int i=1;
        while((row=sheet.getRow(i)) != null){
            String username = row.getCell(0).getStringCellValue();
            String date = row.getCell(1).getStringCellValue();
            int score =(int) row.getCell(2).getNumericCellValue();

            dftm.addRow(new Object[]{username, date, score});
            i++;
        }
        table.setModel(dftm);
        workbook.close();
    }
}
