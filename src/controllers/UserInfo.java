package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.layout.GridPane;
import services.ExcelService;
import services.StageService;


public class UserInfo implements Initializable {

    private StageService stageService = StageService.getStageService();
    private ExcelService excelService = ExcelService.getExcelServiceInstance();

    @FXML private GridPane userInfoPane;

    @FXML
    private JFXTextField name;

    @FXML
    private JFXTextField surname;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXTextField phone;

    @FXML
    private JFXButton endButton;

    private int totalPoints;


    private void bindEndButton() {
        this.endButton.disableProperty().bind(Bindings.isEmpty(name.textProperty())
                .or(Bindings.isEmpty(surname.textProperty()))
                .or(Bindings.isEmpty(email.textProperty()))
        );
    }

    public void setTotalPoints(int totalPoints){
        this.totalPoints = totalPoints;
    }

    public void onEndButtonClicked(ActionEvent event) {
        System.out.println("User info: "+ totalPoints);
        String[] info = new String[]{
            this.name.getText(), this.surname.getText(), this.email.getText(),
                this.phone.getText(), this.totalPoints+""
        };
        this.excelService.saveParticipantInfo(info);
        stageService.changeSceneAndPassPointsToEndScreen("resources/view/endScreen.fxml", event, totalPoints);
    }

//    public void saveParticipantInfo() {
//        try {
//            fis = new FileInputStream("src/resources/entities/Participants.xlsx");
//            wb = WorkbookFactory.create(fis);
//            sh = wb.getSheet("Sheet1");
//            lastRow = sh.getLastRowNum();
//            row = sh.createRow(lastRow + 1);
//
//            cell1 = row.createCell(0);
//            cell2 = row.createCell(1);
//            cell3 = row.createCell(2);
//            cell4 = row.createCell(3);
//
//            cell1.setCellValue(name.getText());
//            cell2.setCellValue(surname.getText());
//            cell3.setCellValue(email.getText());
//            cell4.setCellValue(phone.getText());
//
//            fos = new FileOutputStream("src/resources/entities/Participants.xlsx");
//            wb.write(fos);
//            fos.flush();
//            fos.close();
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.bindEndButton();
    }

}
