package controllers;

import com.jfoenix.controls.JFXButton;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import resources.Resources;
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
    @FXML
    private Label congratulationLbl;
    @FXML
    private Label scoreLbl;
    @FXML
    private Label pointsLbl;
    @FXML
    private Button pressSpace;

    private int totalPoints;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.animationService.animationUpDown(head, 1);
        this.animationService.imageRotation(this.orangePlanet, 4, 360);
        this.pressSpace.requestFocus();
        this.showEndMessage();
        this.delayForSceneChange();
    }

    public void delayForSceneChange() {
        PauseTransition delay = new PauseTransition(Duration.seconds(5));
        delay.setOnFinished(pauseEvent -> {
            stageService.fadeOut(endScreenPane, head, "resources/view/quizStartScreen.fxml");
        });
        delay.play();
    }

    public void setTotalPoints(int receivedPoints) {
        this.totalPoints = receivedPoints;
    }


    private void showEndMessage() {
        PauseTransition pauseTransition3 = new PauseTransition(Duration.millis(1440));
        pauseTransition3.setOnFinished(pauseEvent -> {
            showMessage(this.scoreLbl, (int)(this.totalPoints *  (200.0 / 24)) + " / " + (int) (Resources.maxPoints * (200.0 / 24)), 60);
        });

        PauseTransition pauseTransition4 = new PauseTransition(Duration.millis(1980));
        pauseTransition4.setOnFinished(pauseEvent -> {
            showMessage(this.pointsLbl, "poena!", 60);
        });
        showMessage(this.congratulationLbl, "Čestitamo! Osvojili ste ", 60);
        pauseTransition3.play();
        pauseTransition4.play();
    }

    private void showMessage(Label label, String text, int millis) {
        final IntegerProperty i = new SimpleIntegerProperty(0);
        Timeline timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(
                Duration.millis(millis),
                event -> {
                    if (i.get() > text.length()) {
                        timeline.stop();
                    } else {
                        label.setText(text.substring(0, i.get()));
                        i.set(i.get() + 1);
                    }
                });
        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void buttonPressed(){
//        System.out.println("Button pressed "+this.totalPoints);
//        this.showEndMessage();
//        delayForSceneChange();

    }
}
