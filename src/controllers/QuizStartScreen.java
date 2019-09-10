package controllers;


import com.sun.xml.internal.bind.annotation.OverrideAnnotationOf;
import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;


public class QuizStartScreen implements Initializable {
    @FXML private ImageView head;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        animationUpDown();
    }
}
