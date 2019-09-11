package controllers;

import javafx.animation.*;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EndScreen implements Initializable {
    @FXML
    private ImageView head;
    @FXML
    private GridPane endScreen;
    @FXML
    private ImageView orangePlanet;
    private final double headWidth = 268;
    private final double headHeight = 190;

    private void animationUpDown() {
        double positionY = this.head.getY();
        double positionX = this.head.getX();
        System.out.println(this.head.getX() + " " + this.head.getY());
        Path path = new Path();

        path.getElements().add(new MoveTo(positionX + headWidth / 2, positionY + headHeight / 2));
        path.getElements().add(new LineTo(positionX + headWidth / 2, positionY + headHeight / 2 + 10));
        path.getElements().add(new LineTo(positionX + headWidth / 2, positionY + headHeight / 2));
        path.getElements().add(new LineTo(positionX + headWidth / 2, positionY + headHeight / 2 + 10));
        path.getElements().add(new LineTo(positionX + headWidth / 2, positionY + headHeight / 2));

        PathTransition transition = new PathTransition(Duration.seconds(1), path, head);
        transition.setCycleCount(Animation.INDEFINITE);
//        transition.setAutoReverse(true);
        transition.play();
    }

    public void planetRotation(ImageView image) {
        RotateTransition rt = new RotateTransition(Duration.seconds(4), image);
        rt.setByAngle(360);
        rt.setCycleCount(Animation.INDEFINITE);
        rt.setInterpolator(Interpolator.LINEAR);
        rt.play();
    }

    public void changeScreen() {
        PauseTransition delay = new PauseTransition(Duration.seconds(5));
        delay.setOnFinished(pauseEvent -> {
            this.fadeOut();
            //this.changeToStartScene();
        });
        delay.play();
    }

    public void changeToStartScene() {
        try {
            Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("resources/view/quizStartScreen.fxml"));
            Stage currentStage = (Stage) head.getScene().getWindow();

            currentStage.getScene().setRoot(parent);
        } catch (IOException e){
            System.out.println(e.getStackTrace());
        }
    }

    public void fadeOut(){
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(500));
        fade.setNode(endScreen);
        fade.setFromValue(1);
        fade.setToValue(0);
        fade.setOnFinished(event ->
                this.changeToStartScene()
        );
        fade.play();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        animationUpDown();
        planetRotation(this.orangePlanet);
        changeScreen();
    }
}
