package controllers;


import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import services.AnimationService;
import services.StageService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class QuizStartScreen implements Initializable {
    StageService stageService = StageService.getStageService();
    AnimationService animationService = AnimationService.getAnimationService();
    @FXML private ImageView head;
    @FXML private Button pressSpace;
    @FXML private ImageView orangePlanet;
    @FXML private GridPane quizStartPane;

    public void pressSpaceToContinue(ActionEvent event){
        this.stageService.changeScene("resources/view/questions.fxml", event);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stageService.fadeIn(quizStartPane);
        animationService.animationUpDown(head, 1);
        animationService.imageRotation(orangePlanet,4,360);
        this.rocketAnimation();
        this.pressSpace.requestFocus();
    }

    private void rocketAnimation(){
        final Rectangle rectPath = new Rectangle (0, 0, 40, 40);
        rectPath.setArcHeight(10);
        rectPath.setArcWidth(10);
        rectPath.setFill(Color.ORANGE);

        Path path = new Path();
        path.getElements().add(new MoveTo(0, 0));
//			path.getElements().add(new MoveTo(20,20));

        path.getElements().add(new CubicCurveTo(380, 0, 380, 120, 200, 120));
//			path.getElements().add(new CubicCurveTo(0, 120, 0, 240, 380, 240));
        path.getElements().add(new CubicCurveTo(0, 120, 0, 240, 420, 260));
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(6000));
        pathTransition.setPath(path);
        pathTransition.setNode(rectPath);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(Timeline.INDEFINITE);
        pathTransition.setAutoReverse(false);

        quizStartPane.getChildren().add(rectPath);
        pathTransition.play();
    }

}
