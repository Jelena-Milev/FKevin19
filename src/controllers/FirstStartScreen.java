package controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import services.StageService;

public class FirstStartScreen {

    @FXML
    private JFXButton addQuestion;

    @FXML
    private JFXButton twoMinutesTestButton;

    @FXML
    private JFXButton tenQuestionsButton;

    private StageService stageService = StageService.getStageService();

    public void onTwoMinutesButtonClicked(ActionEvent event) {
        stageService.changeScene("resources/view/quizStartScreen.fxml", event);
    }

}
