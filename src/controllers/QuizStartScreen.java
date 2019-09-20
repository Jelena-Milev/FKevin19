package controllers;


import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.util.Duration;
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
    @FXML private ImageView alien;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.stageService.fadeIn(quizStartPane);
        this.animationService.animationUpDown(head, 1);
        this.animationService.animationUpDown(alien,2);
        this.animationService.imageRotation(orangePlanet,4,360);
        this.rocketAnimation();
        this.pressSpace.requestFocus();
    }

    public void pressSpaceToContinue(ActionEvent event){
        this.stageService.changeScene("resources/view/questions.fxml", event);
    }

    private void rocketAnimation(){

        AnchorPane anchorPane = new AnchorPane();
        ImageView rocket = new ImageView("resources/img/rocket.png");
        rocket.setFitWidth(150);
        rocket.setFitHeight(91);


        Path path = new Path();
        path.getElements().add(new MoveTo(60, 45));

        path.getElements().add(new QuadCurveTo(20, 738, 430, 307));
        path.getElements().add(new CubicCurveTo(1200, -300, 800, 550, 1650,250));
        path.getElements().add(new ClosePath());
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(16000));
        pathTransition.setPath(path);
        pathTransition.setNode(rocket);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(Timeline.INDEFINITE);
        pathTransition.setAutoReverse(false);
        quizStartPane.add(anchorPane, 0, 0, 3, 1);
        anchorPane.getChildren().add(rocket);
        pathTransition.play();
    }

    public void displayPosition(MouseEvent event){
//        this.pressSpace.setText("X = "+event.getSceneX() + " Y = "+event.getSceneY());
    }


}
