package services;

import controllers.EndScreen;
import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class StageService {
    private static StageService stageService;
    private StageService() {
    }

    public static StageService getStageService(){
        if(stageService==null)
            stageService=new StageService();
        return stageService;
    }

    public void changeScene(String newScenePath, Scene currentScene) {
        try {
            Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource(newScenePath));
            Stage currentStage = (Stage) currentScene.getWindow();

            currentStage.getScene().setRoot(parent);
        } catch (IOException e){
            System.out.println(e.getStackTrace());
        }
    }

    public void changeSceneAndPassPoints(String nextScenePath, Pane rootPane, int points) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(nextScenePath));
        Stage currentStage = (Stage) rootPane.getScene().getWindow();
        currentStage.getScene().setRoot(loader.load());
        ((EndScreen)loader.getController()).setPoints(points);
    }

    public void fadeOut(Pane rootPane, String nextScenePath){
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(500));
        fade.setNode(rootPane);
        fade.setFromValue(1);
        fade.setToValue(0);
        fade.setOnFinished(event ->
                changeScene(nextScenePath, rootPane.getScene())
        );
        fade.play();
    }

    public void fadeIn(Pane rootPane){
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.seconds(1));
        fade.setNode(rootPane);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
    }

}
