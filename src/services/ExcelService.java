package services;

import org.apache.poi.ss.usermodel.*;
import resources.Resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelService {

    private final String fileName = "Participants.xlsx";

    private static ExcelService excelServiceInstance = null;

    private ExcelService(){}

    public static ExcelService getExcelServiceInstance(){
        if(excelServiceInstance == null){
            excelServiceInstance = new ExcelService();
        }
        return excelServiceInstance;
    }

    public void saveParticipantInfo(String[] info){
        try {
            FileInputStream fileReader = new FileInputStream(Resources.DATA_LOCATION+this.fileName);
            Workbook workbook = WorkbookFactory.create(fileReader);
            Sheet sheet = workbook.getSheet("Sheet1");
            int lastParticipantRow = sheet.getLastRowNum();
            Row newParticipantRow = sheet.createRow(lastParticipantRow+1);

            for (int i = 0; i < info.length; i++) {
                Cell cell = newParticipantRow.createCell(i);
                cell.setCellValue(info[i]);
            }

            FileOutputStream fileWriter = new FileOutputStream(Resources.DATA_LOCATION+this.fileName);
            workbook.write(fileWriter);
            fileWriter.flush();

            fileReader.close();
            fileWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }




}
