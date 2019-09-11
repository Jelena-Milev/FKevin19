package controllers;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import services.AnimationService;
import services.StageService;

import java.net.URL;
import java.util.ResourceBundle;

public class EndScreen implements Initializable {
    StageService stageService = StageService.getStageService();
    AnimationService animationService = AnimationService.getAnimationService();
    @FXML
    private ImageView head;
    @FXML
    private GridPane endScreenPane;
    @FXML
    private ImageView orangePlanet;

    private int points;

    public void delayForSceneChange() {
        PauseTransition delay = new PauseTransition(Duration.seconds(5));
        delay.setOnFinished(pauseEvent -> {
            stageService.fadeOut(endScreenPane, "resources/view/quizStartScreen.fxml");
        });
        delay.play();
    }

    public void setPoints(int receviedPoints){
        this.points = receviedPoints;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        animationService.animationUpDown(head,1);
        animationService.imageRotation(this.orangePlanet, 4, 360);
        delayForSceneChange();
    }
}
