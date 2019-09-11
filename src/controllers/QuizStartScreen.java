package controllers;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import services.AnimationService;
import services.StageService;

import java.net.URL;
import java.util.ResourceBundle;


public class QuizStartScreen implements Initializable {
    StageService stageService = StageService.getStageService();
    AnimationService animationService = AnimationService.getAnimationService();
    @FXML private ImageView head;
    @FXML private Button pressSpace;
    @FXML private ImageView orangePlanet;
    @FXML private GridPane quizStartPane;

    public void pressSpaceToContinue(){
        System.out.println("Space je pritisnut");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stageService.fadeIn(quizStartPane);
        animationService.animationUpDown(head, 1);
        animationService.imageRotation(orangePlanet,4,360);
        this.pressSpace.requestFocus();
    }
}
