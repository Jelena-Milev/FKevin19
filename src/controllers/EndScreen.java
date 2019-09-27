package controllers;

import javafx.animation.*;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    @FXML private Label congratulationLbl;
    @FXML private Label scoreLbl;
    @FXML private Label pointsLbl;
    @FXML private VBox message;


    private int totalPoints;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        animationService.animationUpDown(head,1);
        animationService.imageRotation(this.orangePlanet, 4, 360);
        this.delayForSceneChange();
    }

    public void delayForSceneChange() {
        PauseTransition delay = new PauseTransition(Duration.seconds(5));
        delay.setOnFinished(pauseEvent -> {
//            stageService.fadeOut(endScreenPane, "resources/view/quizStartScreen.fxml");
            stageService.fadeOut(endScreenPane, head, "resources/view/quizStartScreen.fxml");
        });
        PauseTransition waitForShowingMessage = new PauseTransition(Duration.seconds(1));
        waitForShowingMessage.setOnFinished(event -> {
            showEndMessage();
            delay.play();
        });
        waitForShowingMessage.play();
    }

    public void setTotalPoints(int receivedPoints){
        this.totalPoints = receivedPoints;
        this.congratulationLbl.setVisible(true);
        this.congratulationLbl.setText(this.totalPoints+"");
    }



    private void showEndMessage(){
        StringBuilder builder = new StringBuilder();
        builder.append("Čestitamo! Osvojio/la si \n");
        builder.append(this.totalPoints);
        builder.append(" / ");
        builder.append(Resources.maxPoints);
        builder.append("poena!");

        this.message.setVisible(true);
        this.congratulationLbl.setText(builder.toString());
//        this.showMessage(this.congratulationLbl, builder.toString(), 20);

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


}
