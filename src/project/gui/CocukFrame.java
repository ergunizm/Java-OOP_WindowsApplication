package project.gui;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import project.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class CocukFrame extends JFrame {
    private Cocuk cocuk;
    private Alistirma alistirma;
    private Gunce gunce;
    private int score = 0;

    public CocukFrame() {
        setTitle("Çocuk Frame");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        add(panel);
        Button startButton = new Button("Start",400,200);
        panel.add(startButton);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alistirma.generateSorular();
                gunce = new Gunce(cocuk);
                TimerLabel tLbl = new TimerLabel();
                panel.add(tLbl);
                panel.add(Box.createRigidArea(new Dimension(800,0)));

                for(int i=0;i<alistirma.getN();i++){
                    JLabel madde = new JLabel(String.valueOf(i+1));
                    panel.add(madde);
                    panel.add(new JLabel("-) "+alistirma.getSorular().get(i).getA()+" * "+alistirma.getSorular().get(i).getB() + " = "));
                    TextField resultText = new TextField(1);
                    panel.add(resultText);
                    Button nextSoru = new Button("Next",400,200);
                    panel.add(nextSoru);
                    nextSoru.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            SoruGunce soruGunce;
                            int id = Integer.parseInt(madde.getText())-1;
                            if(resultText.getText().trim().equalsIgnoreCase("")){// Soru boşsa
                                soruGunce = new SoruGunce(id+1, tLbl.getTime(), alistirma.getSorular().get(id).getA(), alistirma.getSorular().get(id).getB(),false);
                            }else{
                                if(Integer.parseInt(resultText.getText()) == alistirma.getSorular().get(id).getResult()){//Soru doğru cevaplandı
                                    System.out.println(resultText.getText()+" "+alistirma.getSorular().get(id).getResult());
                                    soruGunce = new SoruGunce(id+1, tLbl.getTime(), alistirma.getSorular().get(id).getA(), alistirma.getSorular().get(id).getB(),true);
                                    score = score + 20;
                                }else{//Soru yanlış cevaplandı
                                    soruGunce = new SoruGunce(id+1, tLbl.getTime(), alistirma.getSorular().get(id).getA(), alistirma.getSorular().get(id).getB(),false);
                                }
                            }

                            gunce.addAnsweredSoru(soruGunce);
                            score = score - tLbl.getTime();
                            tLbl.resetTimer();
                            nextSoru.setVisible(false);
                            resultText.setVisible(false);
                        }
                    });
                    panel.add(Box.createRigidArea(new Dimension(800,0)));
                }
                startButton.setVisible(false);

                Button finishBtn = new Button("Finish",400,200);
                panel.add(finishBtn);
                finishBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        gunce.setScore(score);
                        //cocuk için özel kaydetme
                        try {
                            String[] row_heading = {"QuestionNumber","a","b","True","Time"};
                            XSSFWorkbook workbook = new XSSFWorkbook();
                            XSSFSheet sheet = workbook.createSheet("Questions");
                            Row headerRow = sheet.createRow(0);

                            // Creating header
                            for (int i = 0; i < row_heading.length; i++) {
                                Cell cell = headerRow.createCell(i);
                                cell.setCellValue(row_heading[i]);
                            }
                            // Creating data rows for each field
                            for(int i = 0; i < gunce.getSorular().size(); i++) {
                                Row dataRow = sheet.createRow(i + 1);
                                dataRow.createCell(0).setCellValue(gunce.getSorular().get(i).getSoruNumber());
                                dataRow.createCell(1).setCellValue(gunce.getSorular().get(i).getA());
                                dataRow.createCell(2).setCellValue(gunce.getSorular().get(i).getB());
                                dataRow.createCell(3).setCellValue(String.valueOf(gunce.getSorular().get(i).isDogruMu()));
                                dataRow.createCell(4).setCellValue(gunce.getSorular().get(i).getSure());
                            }

                            FileOutputStream out = new FileOutputStream( new File(cocuk.getUsername()+".xlsx"));

                            workbook.write(out);
                            out.close();

                            //highscore için kaydetme
                            File file= new File("Highscore.xlsx");
                            if(file.isFile()){
                                FileInputStream inputStream = new FileInputStream(file);
                                Workbook workbook2 = WorkbookFactory.create(inputStream);

                                Sheet sheet2 = workbook2.getSheetAt(0);

                                Row dataRow2 = sheet2.createRow(sheet2.getLastRowNum() + 1);
                                dataRow2.createCell(0).setCellValue(gunce.getCocuk().getUsername());
                                dataRow2.createCell(1).setCellValue(gunce.getDate());
                                dataRow2.createCell(2).setCellValue(gunce.getScore());

                                inputStream.close();
                                FileOutputStream outputStream = new FileOutputStream("Highscore.xlsx");
                                workbook2.write(outputStream);
                                workbook2.close();
                                outputStream.close();
                            }else{
                                String[] row_heading2 = {"Username","Date&Time","Score"};
                                XSSFWorkbook workbook2 = new XSSFWorkbook();
                                XSSFSheet sheet2 = workbook2.createSheet("Highscore");
                                Row headerRow2 = sheet2.createRow(0);

                                for (int i = 0; i < row_heading2.length; i++) {
                                    Cell cell = headerRow2.createCell(i);
                                    cell.setCellValue(row_heading2[i]);
                                }
                                Row dataRow2 = sheet2.createRow(sheet2.getLastRowNum() + 1);
                                dataRow2.createCell(3).setCellValue(gunce.getCocuk().getUsername());
                                dataRow2.createCell(4).setCellValue(gunce.getDate());
                                dataRow2.createCell(5).setCellValue(gunce.getScore());

                                FileOutputStream out2 = new FileOutputStream( new File("Highscore.xlsx"),true);

                                workbook2.write(out2);
                                out2.close();
                            }

                            System.out.println("Gunce has been successfully saved in file.");
                        }catch( IOException ioe ) {
                            System.out.println("An exception has occured during writing to file.");
                            ioe.printStackTrace();
                        }

                        //yeni frame gonder
                        ResultFrame frame = new ResultFrame(gunce);
                        frame.setVisible(true);

                        dispose();
                    }
                });

            }
        });
    }

    public void setCocuk(Cocuk c){
        this.cocuk = c;
    }

    public void setAlistirma(Alistirma alistirma) {
        this.alistirma = alistirma;
    }
}
