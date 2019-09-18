package resources.entities;

import resources.Resources;

public class ClosedQuestion {

    private String question;
    private String correctAnswer;
    private String guessedAnswer;
    private Resources.QuestionDifficulty difficulty;
    private String[] possibleAnswers;

    public ClosedQuestion() {
        this.possibleAnswers = new String[3];
    }

    public String getQuestion() {
        return this.question;
    }

    public String getCorrectAnswer() {
        return this.correctAnswer;
    }

    public String getGuessedAnswer() {
        return this.guessedAnswer;
    }

    public Resources.QuestionDifficulty getDifficulty() {
        return this.difficulty;
    }

    public String[] getPossibleAnswers() {
        return possibleAnswers;
    }

    public void setQuestion(String question) {
        if (!this.validateTextAttribute(question)) {
            throw new IllegalArgumentException("Null or empty");
        }
        this.question = question;
    }

    public void setCorrectAnswer(String correctAnswer) {
        if (!this.validateTextAttribute(correctAnswer)) {
            throw new IllegalArgumentException("Correct answer is either null or empty.");
        }
        this.correctAnswer = correctAnswer;
    }

    public void setGuessedAnswer(String guessedAnswer) {
        if (!this.validateTextAttribute(guessedAnswer)) {
            throw new IllegalArgumentException("Guessed answer is either null or empty.");
        }
        this.guessedAnswer = guessedAnswer;
    }

    public void setDifficulty(Resources.QuestionDifficulty questionDifficulty) {
        if (questionDifficulty == null) {
            throw new IllegalArgumentException("Question difficulty cannot be null");
        }
        this.difficulty = questionDifficulty;
    }

    public void setPossibleAnswers(String[] possibleAnswers){
        if(!this.validatePossibleAnswers(possibleAnswers)){
            throw new IllegalArgumentException("One or more possible answers are null or empty or there is duplicated answers.");
        }
        this.possibleAnswers = possibleAnswers;
    }

    public boolean validatePossibleAnswers(String[] possibleAnswers){
        if(possibleAnswers == null || possibleAnswers.length != 3){
            return false;
        }
        for(String possibleAnswer: possibleAnswers){
            if(!this.validateTextAttribute(possibleAnswer) ||
                    possibleAnswer.toLowerCase().equals(this.correctAnswer.toLowerCase())){
                return false;
            }
        }
        if(getDuplicatedPossibleAnswer(possibleAnswers)!=-1){
            return false;
        }

        return true;
    }

    private int getDuplicatedPossibleAnswer(String[] possibleAnswers) {
        for (int i = 0; i < possibleAnswers.length - 1; i++) {
            for (int j = i + 1; j < possibleAnswers.length; j++) {
                if (possibleAnswers[i].toLowerCase().equals(possibleAnswers[j].toLowerCase()))
                    return i;
            }
        }
        return -1;
    }

    public boolean validateTextAttribute(String text) {
        return text != null && !text.isEmpty();
    }

    public boolean correctAnswer() {
        return this.guessedAnswer.toLowerCase().equals(this.correctAnswer.toLowerCase());
    }

    public boolean validateQuestion() {
        if(!this.validateTextAttribute(this.question)){
            return false;
        }
        if(!this.validateTextAttribute(this.correctAnswer)){
            return false;
        }
        if(!this.validateTextAttribute(this.guessedAnswer)){
            return false;
        }
        if(!this.validatePossibleAnswers(this.possibleAnswers)){
            return false;
        }
        return true;
    }

    protected int getQuestionPoints() {
        if (this.correctAnswer()) {
            if (this.difficulty == Resources.QuestionDifficulty.LOW) {
                return Resources.QuestionDifficulty.LOW.getPoints();
            } else if (this.difficulty == Resources.QuestionDifficulty.MED) {
                return Resources.QuestionDifficulty.MED.getPoints();
            } else {
                return Resources.QuestionDifficulty.HIG.getPoints();
            }
        } else {
            return 0;
        }
    }

    public int addPointsAfterValidating() {
        if (!this.validateQuestion()) {
            throw new IllegalStateException("Questions are missing some attributes.");
        }
        return this.getQuestionPoints();
    }

    @Override
    public String toString(){
        return this.question;
    }
}
