package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXToggleButton;
import com.sun.xml.internal.ws.api.FeatureConstructor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import resources.entities.ClosedQuestion;

import java.awt.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Questions implements Initializable {

    @FXML
    private JFXToggleButton answerOne;

    @FXML
    private JFXToggleButton answerTwo;

    @FXML
    private JFXToggleButton answerThree;

    @FXML
    private JFXToggleButton answerFour;

    @FXML
    private TextArea questionText;

    @FXML
    private JFXProgressBar progressBar;

    @FXML
    private JFXButton nextButton;

    private List<ClosedQuestion> questions;
    private int index = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
