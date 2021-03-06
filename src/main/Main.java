package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import resources.Resources;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Resources.APP_LOCATION = Resources.getAppLocation();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("resources/view/firstStartScreen.fxml"));
        primaryStage.setTitle("FKevin");
        primaryStage.setFullScreenExitHint("");
        primaryStage.setFullScreen(true);
        //primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
