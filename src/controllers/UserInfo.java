package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class UserInfo implements Initializable {

    @FXML
    private JFXTextField name;

    @FXML
    private JFXTextField surname;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXTextField phone;

    @FXML
    private JFXButton endBtn;

    private int totalPoints;

    private void bindEndButton() {
        this.endBtn.disableProperty().bind(Bindings.isEmpty(name.textProperty()).or(Bindings.isEmpty(surname.textProperty())).or(Bindings.isEmpty(email.textProperty())));
    }

    public void setTotalPoints(int totalPoints){
        this.totalPoints = totalPoints;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
