package controllers;

import com.jfoenix.controls.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import resources.entities.ClosedQuestion;
import resources.Resources;
import services.QuestionService;
import services.StageService;

import java.io.IOException;
import java.net.URL;
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

    @FXML
    private GridPane questionsPane;

    private ClosedQuestion[] questions = new ClosedQuestion[100];
    private int currentQuestionIndex = 0;
    private StageService stageService = StageService.getStageService();
    private QuestionService questionService = QuestionService.getQuestionServiceInstance();
    private Timeline timeline;

    PseudoClass centered = PseudoClass.getPseudoClass("centered");


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        questionText.pseudoClassStateChanged(centered, true);
        this.questions = this.questionService.getRandomQuestions();
        for (ClosedQuestion q: questions) {
        }
//        this.setTestQuestions();
        this.guessedAnswer.setVisible(false);
        this.bindNextButton();
        this.questionText.setMouseTransparent(true);
        this.questionText.setFocusTraversable(true);
        this.displayQuestion();
    }

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
        //this.questions.get(currentQuestionIndex).setGuessedAnswer(selectedButton.getText());
        this.questions[currentQuestionIndex].setGuessedAnswer(selectedButton.getText());
        this.guessedAnswer.setText(selectedButton.getText());
        this.deselectButtons();
        selectedButton.setSelected(true);
        this.nextButton.requestFocus();
    }

    private void loadQuestionAnswers(ClosedQuestion question) {
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
//        this.nextButton.disableProperty().bind(Bindings.createBooleanBinding(this::checkIfSelected));
        this.nextButton.disableProperty().bind(Bindings.not(answerOne.selectedProperty().or(answerTwo.selectedProperty().or(answerThree.selectedProperty().or(answerFour.selectedProperty())))));
    }

    private boolean checkIfSelected() {
        if (answerOne.isSelected() || answerTwo.isSelected() || answerThree.isSelected() || answerFour.isSelected()) {
            return false;
        }else{
            return true;
        }
    }

    private void loadQuestionText(ClosedQuestion question) {
        String questionText = question.getQuestionText();
        final IntegerProperty i = new SimpleIntegerProperty(0);
        Timeline timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(
                Duration.millis(40),
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
        if(this.currentQuestionIndex == this.questions.length){
            this.timeline.stop();
            this.gameFinished();
        }
        ClosedQuestion question = this.questions[this.currentQuestionIndex];
        //ClosedQuestion question = this.questions.get(this.currentQuestionIndex);
        this.resetComponents();
        this.loadQuestionText(question);
        this.loadQuestionAnswers(question);
        this.answerOne.setDisableVisualFocus(true);
        if (currentQuestionIndex == 0) {
            this.progressBarCountdown();
        }
    }

    private void progressBarCountdown() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(this.progressBar.progressProperty(), 0)),
                new KeyFrame(Duration.seconds(Resources.gameDurationSeconds), e -> {
                        gameFinished();
                }, new KeyValue(this.progressBar.progressProperty(), 1))
        );
        timeline.setCycleCount(1);
        timeline.play();
    }

    private void gameFinished(){
        int points = this.totalNumberOfPoints();
        stageService.changeSceneAndPassPointsToUserInfoScreen("resources/view/userInfo.fxml", questionsPane, points);
    }

    public void onNextButtonClicked(ActionEvent event) {
        ++this.currentQuestionIndex;
        this.displayQuestion();
        this.deselectButtons();
    }

    private int totalNumberOfPoints() {
        int total = 0;
        for (ClosedQuestion q: this.questions) {
            if (q.getGuessedAnswer() == null) {
                break;
            }
            if (q.getGuessedAnswer().equals(q.getCorrectAnswer())) {
                switch (q.getDifficulty()) {
                    case LOW:
                        total += Resources.QuestionDifficulty.LOW.getPoints();
//                        System.out.println(total);
                        break;
                    case MEDIUM:
                        total += Resources.QuestionDifficulty.MEDIUM.getPoints();
//                        System.out.println(total);
                        break;
                    case HIGH:
                        total += Resources.QuestionDifficulty.HIGH.getPoints();
//                        System.out.println(total);
                        break;
                }
            }else{
                System.out.println("netacan odg");
            }
        }
        return total;
    }
}
