package controllers;


import com.sun.xml.internal.bind.annotation.OverrideAnnotationOf;
import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;


public class QuizStartScreen implements Initializable {
    @FXML private ImageView head;
    @FXML private Button pressSpace;
    @FXML private ImageView orangePlanet;
    @FXML private GridPane quizStart;
    private final double headWidth = 268;
    private final double headHeight = 190;

    private void animationUpDown(){
        double positionY = this.head.getY();
        double positionX = this.head.getX();
        System.out.println(this.head.getX() + " " + this.head.getY());
        Path path = new Path();

        path.getElements().add(new MoveTo(positionX + headWidth/2, positionY + headHeight/2));
        path.getElements().add(new LineTo(positionX + headWidth/2, positionY + headHeight/2+10));
        path.getElements().add(new LineTo(positionX + headWidth/2, positionY + headHeight/2));
        path.getElements().add(new LineTo(positionX + headWidth/2, positionY + headHeight/2+10));
        path.getElements().add(new LineTo(positionX + headWidth/2, positionY + headHeight/2));

        PathTransition transition = new PathTransition(Duration.seconds(1), path, head);
        transition.setCycleCount(Animation.INDEFINITE);
//        transition.setAutoReverse(true);
        transition.play();
    }

    public void planetRotation(ImageView image){
        RotateTransition rt = new RotateTransition(Duration.seconds(4), image);
        rt.setByAngle(360);
        rt.setCycleCount(Animation.INDEFINITE);
        rt.setInterpolator(Interpolator.LINEAR);
        rt.play();
    }
    public void pressSpaceToContinue(){
        System.out.println("Space je pritisnut");
    }
    public void fadeIn(){
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.seconds(1));
        fade.setNode(quizStart);
        fade.setFromValue(0);
        fade.setToValue(1);
//        fade.setOnFinished(event -> {
//
//        });
        fade.play();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fadeIn();
        animationUpDown();
        planetRotation(this.orangePlanet);
        this.pressSpace.requestFocus();
    }
}
