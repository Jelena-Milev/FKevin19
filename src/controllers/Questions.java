package controllers;

import com.jfoenix.controls.*;
import com.sun.xml.internal.ws.api.FeatureConstructor;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.util.Duration;
import resources.entities.ClosedQuestion;

import java.awt.*;
import java.net.URL;
import java.sql.Time;
import java.util.*;
import java.util.List;

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
    private JFXTextArea questionText;

    @FXML
    private JFXProgressBar progressBar;

    @FXML
    private JFXButton nextButton;

    @FXML
    private JFXTextField guessedAnswer;

    private List<ClosedQuestion> questions;
    private int index = 0;

    private void resetComponents() {
        this.answerOne.setText("");
        this.answerTwo.setText("");
        this.answerThree.setText("");
        this.answerFour.setText("");
    }

    private void deselectButtons() {
        this.answerOne.setSelected(false);
        this.answerTwo.setSelected(false);
        this.answerThree.setSelected(false);
        this.answerFour.setSelected(false);
    }

    public void buttonToggled(ActionEvent event) {
        JFXToggleButton selectedButton = (JFXToggleButton) event.getTarget();
        this.questions.get(index).setGuessedAnswer(selectedButton.getText());
        this.guessedAnswer.setText(selectedButton.getText());
        this.deselectButtons();
        selectedButton.setSelected(true);
    }

    private void loadQuestion(ClosedQuestion question) {
        List<String> answers = new ArrayList<>();
        String[] possibleAnswers = question.getPossibleAnswers();
        answers.addAll(Arrays.asList(possibleAnswers));
        answers.add(question.getCorrectAnswer());

        Collections.shuffle(answers);
        this.answerOne.setText(answers.get(0));
        this.answerTwo.setText(answers.get(1));
        this.answerThree.setText(answers.get(2));
        this.answerFour.setText(answers.get(3));
    }

    private void bindNextButton() {
        this.nextButton.disableProperty().bind(Bindings.createBooleanBinding(this::checkIfSelected));
    }

    private boolean checkIfSelected() {
        if (answerOne.isSelected() || answerTwo.isSelected() || answerThree.isSelected() || answerFour.isSelected()) {
            return true;
        }
        return false;
    }

    private void loadQuestionText(ClosedQuestion question) {
        String questionText = question.getQuestion();
        final IntegerProperty i = new SimpleIntegerProperty(0);
        Timeline timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(
                Duration.millis(30),
                event -> {
                    if (i.get() > questionText.length()) {
                        timeline.stop();
                    } else {
                        this.questionText.setText(questionText.substring(0, i.get()));
                        i.set(i.get() + 1);
                    }
                }
        );
        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void displayQuestion() {
        ClosedQuestion question = this.questions.get(this.index);
        this.resetComponents();
        this.loadQuestionText(question);
        this.loadQuestion(question);
        this.answerOne.setDisableVisualFocus(true);
    }

    private void progressBarCountdown() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(progressBar.progressProperty(), 0)),
                new KeyFrame(Duration.minutes(2), e -> {

                }, new KeyValue(progressBar.progressProperty(), 1))
        );
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.guessedAnswer.setVisible(false);
        this.resetComponents();
        this.bindNextButton();
    }
}
