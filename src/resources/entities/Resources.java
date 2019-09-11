package resources.entities;

public class Resources {
    private Resources(){}

    private static int lowDifficultyQuestionPoints = 1;
    private static int medDifficultyQuestionPoints = 3;
    private static int higDifficultyQuestionPoints = 5;

    public enum QuestionDifficulty {
        LOW (lowDifficultyQuestionPoints),
        MED (medDifficultyQuestionPoints),
        HIG (higDifficultyQuestionPoints);

        private final int points;

        QuestionDifficulty(int points) {
            this.points = points;
        }

        public int getPoints() {
            return this.points;
        }
    }



}
