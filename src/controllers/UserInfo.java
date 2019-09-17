package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.poi.ss.usermodel.*;
import services.StageService;


public class UserInfo implements Initializable {

    private static Workbook wb;
    private static Sheet sh;
    private static FileInputStream fis;
    private static FileOutputStream fos;
    private static Row row;
    private static Cell cell1;
    private static Cell cell2;
    private static Cell cell3;
    private static Cell cell4;
    private int lastRow;

    @FXML
    private JFXTextField name;

    @FXML
    private JFXTextField surname;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXTextField phone;

    @FXML
    private JFXButton endBtn;

    private int totalPoints;
    private StageService stageService = StageService.getStageService();

    private void bindEndButton() {
        this.endBtn.disableProperty().bind(Bindings.isEmpty(name.textProperty())
                .or(Bindings.isEmpty(surname.textProperty()))
                .or(Bindings.isEmpty(email.textProperty()))
        );
    }

    public void setTotalPoints(int totalPoints){
        this.totalPoints = totalPoints;
    }

    public void onEndButtonClicked(ActionEvent event) {
        this.saveParticipantInfo();
        stageService.changeSceneAndPassPoints("resources/view/endScreen.fxml", event, totalPoints);
    }

    public void saveParticipantInfo() {
        try {
            fis = new FileInputStream("src/resources/entities/Participants.xlsx");
            wb = WorkbookFactory.create(fis);
            sh = wb.getSheet("Sheet1");
            lastRow = sh.getLastRowNum();
            row = sh.createRow(lastRow + 1);

            cell1 = row.createCell(0);
            cell2 = row.createCell(1);
            cell3 = row.createCell(2);
            cell4 = row.createCell(3);

            cell1.setCellValue(name.getText());
            cell2.setCellValue(surname.getText());
            cell3.setCellValue(email.getText());
            cell4.setCellValue(phone.getText());

            fos = new FileOutputStream("src/resources/entities/Participants.xlsx");
            wb.write(fos);
            fos.flush();
            fos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
