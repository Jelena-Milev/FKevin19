package resources.entities;

public class Resources {
    private Resources(){}

    public static int lowDifficultyQuestionPoints = 1;
    public static int medDifficultyQuestionPoints = 3;
    public static int higDifficultyQuestionPoints = 5;

    public enum QuestionDifficulty {
        LOW (lowDifficultyQuestionPoints),
        MED (medDifficultyQuestionPoints),
        HIG (higDifficultyQuestionPoints);

        public final int points;

        QuestionDifficulty(int points) {
            this.points = points;
        }

        public int getPoints() {
            return this.points;
        }
    }



}
